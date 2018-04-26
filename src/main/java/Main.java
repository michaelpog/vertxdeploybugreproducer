import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class Main {

    public static void main(String [] args) {
        Vertx vertx = Vertx.vertx();
        WebServerStarter webServerStarter = new WebServerStarter();

        int threads = Runtime.getRuntime().availableProcessors();
        System.out.println("Creating " + threads+ " threads");
        for (int i = 0; i < threads; i++) {
            vertx.deployVerticle(webServerStarter, event -> {
                System.out.println("Deployed on Vertx Thread " + Thread.currentThread().getName());
            });
        }
    }

    public static class WebServerStarter extends AbstractVerticle {

        @Override
        public void start()  {
            System.out.println("Current Vertx Thread "+ Thread.currentThread().getName());
            HttpServer httpServer  = getVertx().createHttpServer();
            httpServer.listen();
        }
    }
}
