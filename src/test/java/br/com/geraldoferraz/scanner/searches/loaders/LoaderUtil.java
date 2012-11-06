package br.com.geraldoferraz.scanner.searches.loaders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URISyntaxException;

import br.com.geraldoferraz.scanner.searches.loaders.stream.MyJarInputStreamTest;

public class LoaderUtil {

	private String rootPath;
	private String defaultPackage;
	private String separator;

	private File rootFolder;
	private File clazz;
	private File folder;
	private File clazzInFolder;
	private File sourceFolderBin;
	private File sourceFolderTest;

	private static final String EMPTY_JAR = "ClassPathEmptyJar.jar";
	private static final String NOT_EMPTY_JAR = "ClassPathNotEmptyJar.jar";

	private File emptyJarFile;
	private File notEmptyJarFile;

	public LoaderUtil() {
		createMocks();
		initializeMocks();

	}

	private void createMocks() {

		rootPath = "/home/workspace/project";
		defaultPackage = "/br/com/geraldo";
		separator = System.getProperty("file.separator");

		rootFolder = mock(File.class);
		clazz = mock(File.class);
		folder = mock(File.class);
		clazzInFolder = mock(File.class);
		sourceFolderBin = mock(File.class);
		sourceFolderTest = mock(File.class);

		try {
			emptyJarFile = new File(MyJarInputStreamTest.class.getResource("/" + EMPTY_JAR).toURI());
			notEmptyJarFile = new File(MyJarInputStreamTest.class.getResource("/" + NOT_EMPTY_JAR).toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException("Arquivo para teste não encontrado");
		}

	}

	private void initializeMocks() {

		when(sourceFolderBin.isDirectory()).thenReturn(true);
		when(sourceFolderTest.isDirectory()).thenReturn(true);

		when(sourceFolderBin.getPath()).thenReturn(rootPath);
		when(sourceFolderTest.getPath()).thenReturn(rootPath);

		when(sourceFolderBin.listFiles()).thenReturn(new File[] { rootFolder });
		when(sourceFolderTest.listFiles()).thenReturn(new File[] { rootFolder });

		when(rootFolder.isDirectory()).thenReturn(true);
		when(rootFolder.listFiles()).thenReturn(new File[] { clazz, folder, clazzInFolder });

		when(folder.isDirectory()).thenReturn(true);
		when(folder.listFiles()).thenReturn(new File[] { clazzInFolder });

		when(clazz.isDirectory()).thenReturn(false);
		when(clazzInFolder.isDirectory()).thenReturn(false);

		when(clazz.isFile()).thenReturn(true);
		when(clazzInFolder.isFile()).thenReturn(true);

		when(clazz.getName()).thenReturn("Classe.class");
		when(clazzInFolder.getName()).thenReturn("Classe.class");

		when(clazz.getAbsolutePath()).thenReturn(rootPath + defaultPackage + separator + "Classe.class");
		when(clazzInFolder.getAbsolutePath()).thenReturn(
				rootPath + defaultPackage + separator + "package" + separator + "Classe.class");

	}

	public String getRootPath() {
		return rootPath;
	}

	public File getRootFolder() {
		return rootFolder;
	}

	public File getClazz() {
		return clazz;
	}

	public File getSourceFolderBin() {
		return sourceFolderBin;
	}

	public File getSourceFolderTest() {
		return sourceFolderTest;
	}

	public File getEmptyJarFile() {
		return emptyJarFile;
	}

	public File getNotEmptyJarFile() {
		return notEmptyJarFile;
	}

}
