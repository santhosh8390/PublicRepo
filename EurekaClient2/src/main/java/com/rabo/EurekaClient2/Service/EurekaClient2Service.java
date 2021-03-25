package com.rabo.EurekaClient2.Service;

import java.io.FileNotFoundException;

public interface EurekaClient2Service {

    String getCity();

    String getEmailUsingHystrix() throws FileNotFoundException;
}
