package stubs.com.nab.ms.test.application;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.StubServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
public class SampleStub {

    private static StubServer sampleStubServer;

    @Bean
    WireMockServer sampleStubServer(
            @Value("${stubs.test.application.useFileMapping:false}") boolean useFileMapping) {

        WireMockConfiguration wireMockConfiguration = options()
                .port(9001)
                .dynamicHttpsPort()
                .bindAddress("localhost")
                .extensions(new ResponseTemplateTransformer(true));
        if (useFileMapping) {
            wireMockConfiguration.usingFilesUnderClasspath("src/test/resources/stubdata/locations/sample");
        }
        return new WireMockServer(wireMockConfiguration);
    }
}
