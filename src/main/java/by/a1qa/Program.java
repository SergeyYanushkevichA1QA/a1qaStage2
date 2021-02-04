package by.a1qa;

import by.a1qa.service.ConfProperties;
import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Program {

    public static void main(String[] args) throws IOException, APIException {
        APIClient client = new APIClient("https://tr.a1qa.com");
        client.setUser("s.yanushkevich@qa-academy.by");
        client.setPassword("5604215deZ");
        Map dataSuite = new HashMap();
        dataSuite.put("name", "Alert TS");
        dataSuite.put("description", "Automate testing Alerts on http://the-internet.herokuapp.com/javascript_alerts");
        Map dataSection = new HashMap();
        dataSection.put("name", "Alerts Tests");
        dataSection.put("suite_id", 19307);
        JSONObject section = (JSONObject) client.sendPost("add_section/140", dataSection);
        Map dataCase = new HashMap();
        dataCase.put("title", "Alert TC");
        JSONObject testcase = (JSONObject) client.sendPost("add_case/" + section.get("id"), dataCase);
        Map dataRun = new HashMap();
        dataRun.put("name", "5 alerts tests");
        dataRun.put("suite_id", dataSection.get("suite_id"));
        JSONObject run = (JSONObject) client.sendPost("add_run/140", dataRun);
        Map dataResult = new HashMap();
        dataResult.put("status_id", 1);
        dataResult.put("comment", "Alert test was passed");
        JSONObject result = (JSONObject)client.sendPost("add_result_for_case/" + run.get("id") + "/" + testcase.get("id"), dataResult);
        client.sendPost("add_attachment_to_result/" + result.get("id"),
                ConfProperties.getProperty("screenPath"));
    }
}
