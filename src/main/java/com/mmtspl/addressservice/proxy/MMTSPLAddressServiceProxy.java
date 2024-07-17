package com.mmtspl.addressservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="mmtspl-netflix-zuul-api-gateway-server")
public class MMTSPLAddressServiceProxy {

}
