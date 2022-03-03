package stubs.com.nab.ms.test.application;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

@Configuration
@EnableAutoConfiguration
@ComponentScan("stubs.com.nab.ms.test.application")
@Import({SampleStub.class})
public class StubApplication {

    @Inject
    private WireMockServer sampleStubServer;

    @PostConstruct
    public void startStubs() {
        startStub(sampleStubServer);
    }

    @PreDestroy
    public void StopStub(){
        stopStub(sampleStubServer);
    }

    private static void startStub(final WireMockServer wireMockServer) {
        wireMockServer.start();
    }

    private static void stopStub(final WireMockServer wireMockServer) {
        wireMockServer.stop();
    }

    public static void main(String[] args) {
        //System.setProperty("spring.profile.active", "test");
        System.setProperty("spring.main.web-application-type", "none");

        startStub(new SampleStub().sampleStubServer(true));
    }
}
