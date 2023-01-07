package com.badajoz.badajozentubolsillo.utils

import android.util.Base64
import android.util.Log
import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

actual inline fun <reified T : Encryptable> T.encrypt(production: Boolean): String {
    val json = Json.encodeToString(this)
    if (!production) {
        return json
    }
    return Helper.encrypt(json)
}

actual inline fun <reified T : Encryptable> String.decrypt(production: Boolean): T {
    val decrypted = when (production) {
        true -> Helper.decrypt(this)
        false -> this
    }
    return Json.decodeFromString(decrypted)
}

object Helper {
    private fun md5(md5: String): String {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        val array: ByteArray = md.digest(md5.toByteArray())
        val sb = StringBuffer()
        for (i in array.indices) {
            sb.append(Integer.toHexString(array[i].toInt() and 0xFF or 0x100).substring(1, 3))
        }
        return sb.toString()
    }

    fun encrypt(value: String): String {
        val iv = ENCRYPTION_IV
        val key = md5(ENCRYPTION_KEY)

        Log.i("MainActivity - Key: ", key)
        val ivParameterSpec = IvParameterSpec(iv.toByteArray(charset("UTF-8")))
        val secretKeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
        val crypted = cipher.doFinal(value.toByteArray(charset("UTF-8")))
        val base64 = Base64.encodeToString(crypted, Base64.NO_WRAP)
        return iv + base64
    }

    fun decrypt(input: String): String {
        val iv = input.substring(0, 16)
        val crypted = input.substring(16, input.replace("\"", "").length)
        val key = md5(ENCRYPTION_KEY)

        val ivParameterSpec = IvParameterSpec(iv.toByteArray(charset("UTF-8")))
        val skeySpec = SecretKeySpec(key!!.toByteArray(charset("UTF-8")), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec)
        val base64 = Base64.decode(crypted, Base64.NO_WRAP)
        val original = cipher.doFinal(base64)
        return String(original)
    }
}