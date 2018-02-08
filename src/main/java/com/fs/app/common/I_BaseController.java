package com.fs.app.common;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

public class I_BaseController {
	public Logger logger = Logger.getLogger(getClass());

	public String getHeaderValue(HttpHeaders headers, String key) {
		return headers.getFirst(key);
	}

	public String getHost(HttpHeaders headers) {
		return headers.getFirst("host");
	}

	public String getOS(HttpHeaders headers) {
		return headers.getFirst("os");
	}

	public String getOS(HttpHeaders headers, String osParams) {
		String os = getOS(headers);
		if (StringUtils.isEmpty(os)) {
			os = osParams;
		}

		return os;
	}

	public String getAppVersion(HttpHeaders headers) {
		return headers.getFirst("version");
	}

	public String getAppVersionName(HttpHeaders headers) {
		return headers.getFirst("versionName");
	}

	public String getUUID(HttpHeaders headers) {
		return headers.getFirst("uuid");
	}

	public String getChannel(HttpHeaders headers) {
		return headers.getFirst("channel");
	}

	public String getClientId(HttpHeaders headers) {
		return headers.getFirst("clientId");
	}

	public String getToken(HttpHeaders headers) {
		return headers.getFirst("token");
	}

}
