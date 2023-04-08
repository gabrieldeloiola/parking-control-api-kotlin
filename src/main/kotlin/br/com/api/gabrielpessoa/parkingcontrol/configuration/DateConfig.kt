package br.com.api.gabrielpessoa.parkingcontrol.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter

@Configuration
class DateConfig(
    val DATETIME_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    val LOCAL_DATETIME_SERIALIZER: LocalDateTimeSerializer =
        LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT))
) {
    fun objectMapper(): ObjectMapper {
        val module = JavaTimeModule()
        module.addDeserializer(LOCAL_DATETIME_SERIALIZER)
        return ObjectMapper().registerModule(module)
    }


}

private fun JavaTimeModule.addDeserializer(localDatetimeSerializer: LocalDateTimeSerializer) {
}
