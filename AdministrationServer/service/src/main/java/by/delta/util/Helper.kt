package by.delta.util

import java.util.ArrayList
import java.util.HashMap

object Helper {

    fun getWraperId(id: Long): Map<String, List<String>> {
        val allIdParams = HashMap<String, List<String>>()
        val idList = ArrayList<String>()
        idList.add(id.toString())
        allIdParams[ConstParamService.ID] = idList
        return allIdParams
    }

    fun getWraperName(name: String): Map<String, List<String>> {
        val allNameParams = HashMap<String, List<String>>()
        val nameList = ArrayList<String>()
        nameList.add(name)
        allNameParams[ConstParamService.NAME] = nameList
        return allNameParams
    }

    fun getWraperEmail(email: String): Map<String, List<String>> {
        val allEmailParams = HashMap<String, List<String>>()
        val emailList = ArrayList<String>()
        emailList.add(email)
        allEmailParams[ConstParamService.EMAIL] = emailList
        return allEmailParams
    }

    fun getWraperUNPOrganization(unp: String): Map<String, List<String>> {
        val allUnpParams = HashMap<String, List<String>>()
        val unpList = ArrayList<String>()
        unpList.add(unp)
        allUnpParams[ConstParamService.UNP_ORG] = unpList
        return allUnpParams
    }

}