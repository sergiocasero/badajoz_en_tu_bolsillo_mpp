import com.badajoz.badajozentubolsillo.model.EncryptedNetworkRequest
import com.badajoz.badajozentubolsillo.model.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.model.FmdUserRequest
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.encrypt
import org.junit.Assert.assertEquals
import org.junit.Test

class CypherTests {

    @Test
    fun encryptFunctionShouldEncryptAnEncryptableDataClass() {
        // Given
        val input = FmdUserRequest(
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

        val expected = FmdUserRequest(
            username = "08810165S",
            password = "0000"
        )

        // When
        val decrypted = input.result.decrypt<FmdUserRequest>()

        // Then
        assertEquals(expected, decrypted)
    }

    @Test
    fun encryptFunctionShouldDoNothingIfProductionIsFalse() {
        // Given
        val input = FmdUserRequest(
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

        val expected = FmdUserRequest(
            username = "08810165S",
            password = "0000"
        )

        // When
        val decrypted = input.result.decrypt<FmdUserRequest>(production = false)

        // Then
        assertEquals(expected, decrypted)
    }

}