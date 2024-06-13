package edu.mirea.onebeattrue.features.medicaldata

import edu.mirea.onebeattrue.database.medical.MedicalDataDto
import edu.mirea.onebeattrue.database.medical.MedicalDataRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class MedicalDataController {

    suspend fun createMedicalData(call: ApplicationCall) {
        val medicalDataDto = call.receive<MedicalDataDto>()
        val id = MedicalDataRepository.createMedicalData(medicalDataDto)
        call.respond(HttpStatusCode.Created, id)
    }

    suspend fun getMedicalDataById(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")

        val medicalData = MedicalDataRepository.getMedicalDataById(id)
        if (medicalData != null) {
            call.respond(HttpStatusCode.OK, medicalData)
        } else {
            call.respond(HttpStatusCode.NotFound, "Medical data not found")
        }
    }

    suspend fun updateMedicalData(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")
        val medicalDataDto = call.receive<MedicalDataDto>().copy(id = id)

        val updated = MedicalDataRepository.updateMedicalData(medicalDataDto)
        if (updated) {
            call.respond(HttpStatusCode.OK, "Medical data updated")
        } else {
            call.respond(HttpStatusCode.NotFound, "Medical data not found")
        }
    }

    suspend fun deleteMedicalData(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")

        val deleted = MedicalDataRepository.deleteMedicalData(id)
        if (deleted) {
            call.respond(HttpStatusCode.OK, "Medical data deleted")
        } else {
            call.respond(HttpStatusCode.NotFound, "Medical data not found")
        }
    }

    suspend fun getMedicalDataByPetId(call: ApplicationCall) {
        val petId = call.parameters["petId"]?.toIntOrNull()
            ?: throw BadRequestException("Pet ID can't be null or non-numeric")

        val medicalDataList = MedicalDataRepository.getMedicalDataByPetId(petId)
        call.respond(HttpStatusCode.OK, medicalDataList)
    }
}
