package by.delta.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);

    public static Map<String, List<String>> getWraperId(final Long id){
        Map<String, List<String>> allIdParams = new HashMap<>();
        List<String> idList = new ArrayList<>();
        idList.add(id.toString());
        allIdParams.put(ConstParamService.ID, idList);
        return allIdParams;
    }

    public static Map<String,List<String>> getWraperName(final String name){
        Map<String, List<String>> allNameParams = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        nameList.add(name);
        allNameParams.put(ConstParamService.NAME, nameList);
        return allNameParams;
    }

    public static Map<String,List<String>> getWraperEmail(final String email){
        Map<String, List<String>> allEmailParams = new HashMap<>();
        List<String> emailList = new ArrayList<>();
        emailList.add(email);
        allEmailParams.put(ConstParamService.EMAIL, emailList);
        return allEmailParams;
    }
}