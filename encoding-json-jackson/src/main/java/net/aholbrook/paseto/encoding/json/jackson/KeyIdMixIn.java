package net.aholbrook.paseto.encoding.json.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class KeyIdMixIn {
	@JsonProperty("kid") abstract String getKeyId();
	@JsonProperty("kid") abstract String setKeyId(String keyId);
}
