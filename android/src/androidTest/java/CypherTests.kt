import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser
import com.badajoz.badajozentubolsillo.model.request.EncryptedNetworkRequest
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.encrypt
import org.junit.Assert.assertEquals
import org.junit.Test

class CypherTests {

    private val appConfigData = AppConfigData(
        user = "android",
        pass = "android",
        key = "androidandroidandroidandroidffff",
        iv = "androidandroidan"
    )

    @Test
    fun encryptFunctionShouldEncryptAnEncryptableDataClass() {
        // Given
        val input = FmdUser(
            username = "08810165S",
            password = "0000"
        )

        val expected = EncryptedNetworkRequest(
            request = "androidandroidanUiXItgcTL3+uxje1wjZDrJqdmb/Dm9f7HfhvGoiDiSOiYdgeA2RutOVcAqlZ7kiv"
        )

        // When
        val encrypted = input.encrypt(key = appConfigData.key, iv = appConfigData.iv)

        // Then
        assertEquals(expected.request, encrypted)
    }

    @Test
    fun decryptFunctionShouldDecryptAnEncryptableDataClass() {
        // Given
        val input = EncryptedNetworkResponse(
            result = "androidandroidanUiXItgcTL3+uxje1wjZDrJqdmb/Dm9f7HfhvGoiDiSOiYdgeA2RutOVcAqlZ7kiv"
        )

        val expected = FmdUser(
            username = "08810165S",
            password = "0000"
        )

        // When
        val decrypted = input.result.decrypt<FmdUser>(appConfigData.key)

        // Then
        assertEquals(expected, decrypted)
    }

    @Test
    fun encryptFunctionShouldDoNothingIfProductionIsFalse() {
        // Given
        val input = FmdUser(
            username = "08810165S",
            password = "0000"
        )

        val expected = EncryptedNetworkRequest(
            request = "{\"username\":\"08810165S\",\"password\":\"0000\"}"
        )

        // When
        val encrypted = input.encrypt(appConfigData.key, appConfigData.iv, production = false)

        // Then
        assertEquals(expected.request, encrypted)
    }

    @Test
    fun decryptFunctionShouldDoNothingIfProductionIsFalse() {
        // Given
        val input = EncryptedNetworkResponse(
            result = "{\"username\":\"08810165S\",\"password\":\"0000\"}"
        )

        val expected = FmdUser(
            username = "08810165S",
            password = "0000"
        )

        // When
        val decrypted = input.result.decrypt<FmdUser>(appConfigData.key, production = false)

        // Then
        assertEquals(expected, decrypted)
    }

}