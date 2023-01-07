import com.badajoz.badajozentubolsillo.model.request.EncryptedNetworkRequest
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.encrypt
import org.junit.Assert.assertEquals
import org.junit.Test

class CypherTests {

    @Test
    fun encryptFunctionShouldEncryptAnEncryptableDataClass() {
        // Given
        val input = com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser(
            username = "08810165S",
            password = "0000"
        )

        val expected = EncryptedNetworkRequest(
            request = "durn58fjkkhH5JK1naxoigjhoVBQkA6PspI/1Dh0B5glUit89kSg5IBoQggEoaFShNt/Onm2rwC1BuPG"
        )

        // When
        val encrypted = input.encrypt()

        // Then
        assertEquals(expected.request, encrypted)
    }

    @Test
    fun decryptFunctionShouldDecryptAnEncryptableDataClass() {
        // Given
        val input = EncryptedNetworkResponse(
            result = "durn58fjkkhH5JK1naxoigjhoVBQkA6PspI/1Dh0B5glUit89kSg5IBoQggEoaFShNt/Onm2rwC1BuPG"
        )

        val expected = com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser(
            username = "08810165S",
            password = "0000"
        )

        // When
        val decrypted = input.result.decrypt<com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser>()

        // Then
        assertEquals(expected, decrypted)
    }

    @Test
    fun encryptFunctionShouldDoNothingIfProductionIsFalse() {
        // Given
        val input = com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser(
            username = "08810165S",
            password = "0000"
        )

        val expected = EncryptedNetworkRequest(
            request = "{\"username\":\"08810165S\",\"password\":\"0000\"}"
        )

        // When
        val encrypted = input.encrypt(production = false)

        // Then
        assertEquals(expected.request, encrypted)
    }

    @Test
    fun decryptFunctionShouldDoNothingIfProductionIsFalse() {
        // Given
        val input = EncryptedNetworkResponse(
            result = "{\"username\":\"08810165S\",\"password\":\"0000\"}"
        )

        val expected = com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser(
            username = "08810165S",
            password = "0000"
        )

        // When
        val decrypted = input.result.decrypt<com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser>(production = false)

        // Then
        assertEquals(expected, decrypted)
    }

}