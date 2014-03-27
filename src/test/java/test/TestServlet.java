/*package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.glassfish.embeddable.archive.ScatteredArchive;
import org.glassfish.jersey.client.ClientResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.enterprise.security.acl.WebResource;

public class TestServlet {

	private static final Logger LOG = Logger.getLogger(TestServlet.class
			.getCanonicalName());
	private static GlassFish glassfish = null;
	private static final String WEB_APP_NAME = "RestTemp";
	private static final String BASE_URI = "http://localhost:" + 8080 + "/"
			+ WEB_APP_NAME;
	private static final String REST_URI = BASE_URI + "/" + "resources" + "/"
			+ "generic";

	@BeforeClass
	public static void startServer() {
		try {
			GlassFishProperties gfProps = new GlassFishProperties();
			gfProps.setPort("http-listener", 8080); // NB: not sure where name
													// comes from - a standard
													// property?

			glassfish = GlassFishRuntime.bootstrap().newGlassFish(gfProps);
			glassfish.start();

			Deployer deployer = glassfish.getDeployer();
			ScatteredArchive archive = new ScatteredArchive(WEB_APP_NAME,
					ScatteredArchive.Type.WAR);
			File buildDir = new File("build", "classes"); // NB: location
															// depends on IDE
															// setup
			archive.addClassPath(buildDir);
			deployer.deploy(archive.toURI());
		} catch (GlassFishException ex) {
			LOG.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
	}

	@AfterClass
	public static void shutDownServer() {
		if (glassfish != null) {
			try {
				glassfish.stop();
				glassfish.dispose();
				glassfish = null;
			} catch (GlassFishException ex) {
				LOG.log(Level.SEVERE, "tearDownClass(): exception: ", ex);
			}
		}
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testPing() throws MalformedURLException, IOException {
		URL url = new URL(BASE_URI + "/hello");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();

		InputStream inputStream = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream));
		assertEquals(GenericResource.MESSAGE, br.readLine());
	}

	@Test
	public void testGet() {
		WebResource r = Client.create().resource(REST_URI);
		ClientResponse cr = r.accept(MediaType.TEXT_PLAIN).get(
				ClientResponse.class); // GET
		String crEntityStr = cr.getEntity(String.class);
		ClientResponse.Status crStatus = cr.getClientResponseStatus();
		assertEquals(GenericResource.MESSAGE, crEntityStr);
		assertEquals(ClientResponse.Status.OK, crStatus);
	}
}

*/