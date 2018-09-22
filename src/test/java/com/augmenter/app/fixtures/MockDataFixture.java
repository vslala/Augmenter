package com.augmenter.app.fixtures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;

import com.dialogflow.response.vo.WebhookRequestVO;
import com.dialogflow.utils.ChatbotUtil;

public class MockDataFixture {
	private MockDataFixture() {}
	
	private static final String WEBHOOK_REQUEST_BASE64_ENCODED = "ICcgICB7ICAnICArIAogJyAgICAgInJlc3BvbnNlSWQiOiAiM2I1ZTIyNjQtOWMyMi00Y2E2LWE5NWEtNzk2MjgzYzQyNTliIiwgICcgICsgCiAnICAgICAicXVlcnlSZXN1bHQiOiB7ICAnICArIAogJyAgICAgICAicXVlcnlUZXh0IjogImdpdmUgZGVwb3NpdHMgc3VtbWFyeSBmb3IgMjR0aCBmZWIgMjAxOCIsICAnICArIAogJyAgICAgICAiYWN0aW9uIjogImRlcG9zaXRzLnJlcG9ydC5zdW1tYXJ5IiwgICcgICsgCiAnICAgICAgICJwYXJhbWV0ZXJzIjogeyAgJyAgKyAKICcgICAgICAgICAiZGF0ZSI6ICIyMDE4LTAyLTI0VDEyOjAwOjAwKzA1OjMwIiAgJyAgKyAKICcgICAgICAgfSwgICcgICsgCiAnICAgICAgICJhbGxSZXF1aXJlZFBhcmFtc1ByZXNlbnQiOiB0cnVlLCAgJyAgKyAKICcgICAgICAgImZ1bGZpbGxtZW50TWVzc2FnZXMiOiBbICAnICArIAogJyAgICAgICAgIHsgICcgICsgCiAnICAgICAgICAgICAidGV4dCI6IHsgICcgICsgCiAnICAgICAgICAgICAgICJ0ZXh0IjogWyAgJyAgKyAKICcgICAgICAgICAgICAgICAiIiAgJyAgKyAKICcgICAgICAgICAgICAgXSAgJyAgKyAKICcgICAgICAgICAgIH0gICcgICsgCiAnICAgICAgICAgfSAgJyAgKyAKICcgICAgICAgXSwgICcgICsgCiAnICAgICAgICJvdXRwdXRDb250ZXh0cyI6IFsgICcgICsgCiAnICAgICAgICAgeyAgJyAgKyAKICcgICAgICAgICAgICJuYW1lIjogInByb2plY3RzL2JlLW15LWFmaWNpb25hZG8tMTQ5MTk2OTU0MDU5OS9hZ2VudC9zZXNzaW9ucy80N2RlNzk0Zi1kYjE5LTY5NmQtNGNjOS1iMTMzYjQ4NDczMTkvY29udGV4dHMvZGVwb3NpdHNyZXBvcnQtZm9sbG93dXAiLCAgJyAgKyAKICcgICAgICAgICAgICJsaWZlc3BhbkNvdW50IjogMiwgICcgICsgCiAnICAgICAgICAgICAicGFyYW1ldGVycyI6IHsgICcgICsgCiAnICAgICAgICAgICAgICJkYXRlIjogIjIwMTgtMDItMjRUMTI6MDA6MDArMDU6MzAiLCAgJyAgKyAKICcgICAgICAgICAgICAgImRhdGUub3JpZ2luYWwiOiAiMjR0aCBmZWIgMjAxOCIgICcgICsgCiAnICAgICAgICAgICB9ICAnICArIAogJyAgICAgICAgIH0gICcgICsgCiAnICAgICAgIF0sICAnICArIAogJyAgICAgICAiaW50ZW50IjogeyAgJyAgKyAKICcgICAgICAgICAibmFtZSI6ICJwcm9qZWN0cy9iZS1teS1hZmljaW9uYWRvLTE0OTE5Njk1NDA1OTkvYWdlbnQvaW50ZW50cy80NTg5YjQxYi1mN2FiLTQyNzAtYWM4NC03YzI4MTYxN2EwODAiLCAgJyAgKyAKICcgICAgICAgICAiZGlzcGxheU5hbWUiOiAiRGVwb3NpdHMuUmVwb3J0IiAgJyAgKyAKICcgICAgICAgfSwgICcgICsgCiAnICAgICAgICJpbnRlbnREZXRlY3Rpb25Db25maWRlbmNlIjogMSwgICcgICsgCiAnICAgICAgICJsYW5ndWFnZUNvZGUiOiAiZW4iICAnICArIAogJyAgICAgfSwgICcgICsgCiAnICAgICAib3JpZ2luYWxEZXRlY3RJbnRlbnRSZXF1ZXN0IjogeyAgJyAgKyAKICcgICAgICAgInBheWxvYWQiOiB7fSAgJyAgKyAKICcgICAgIH0sICAnICArIAogJyAgICAgInNlc3Npb24iOiAicHJvamVjdHMvYmUtbXktYWZpY2lvbmFkby0xNDkxOTY5NTQwNTk5L2FnZW50L3Nlc3Npb25zLzQ3ZGU3OTRmLWRiMTktNjk2ZC00Y2M5LWIxMzNiNDg0NzMxOSIgICcgICsgCiAnICB9ICAnIDsg";
	public static String WEBHOOK_REQUEST_JSON;
	public static WebhookRequestVO WEBHOOK_REQUEST_OBJECT;
	
	static {
		URL filepath = MockDataFixture.class.getClassLoader().getResource("webhook_request.json");
		try(BufferedReader br =  new BufferedReader(
				new FileReader(
						new File(filepath.getPath())))){
			StringBuilder sb = new StringBuilder("");
			String line = "";
			while (null != (line = br.readLine())) 
				sb.append(line);
			WEBHOOK_REQUEST_JSON = sb.toString();
			WEBHOOK_REQUEST_OBJECT = ChatbotUtil.convertJsonToObject(WEBHOOK_REQUEST_JSON, WebhookRequestVO.class);
		} catch (IOException e) {
		}
	}
	
	public static DataSource getDataSource() {
		return DataSourceBuilder
		        .create()
		        .username("root")
		        .password("")
		        .url("jdbc:mysql://localhost:3306/bma_augmenter")
		        .driverClassName("com.mysql.jdbc.Driver")
		        .build();
	}
}
