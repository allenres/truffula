import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TruffulaOptionsTest {

  @Test
  void testValidDirectoryIsSet(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-nc", "-h", directoryPath};

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert: Check that the root directory is set correctly
    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertTrue(options.isShowHidden());
    assertFalse(options.isUseColor());
  }

  @Test
  void testValidDirectoryIsSetReverseOrder(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-h", "-nc", directoryPath};

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert: Check that the root directory is set correctly
    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertTrue(options.isShowHidden());
    assertFalse(options.isUseColor());
  }

  @Test
  void testPathArgOnly(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {directoryPath};

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertFalse(options.isShowHidden());
    assertTrue(options.isUseColor());
  }

  @Test
  void testValidArgsBadFlag(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-a", "-h", directoryPath};

    assertThrows(IllegalArgumentException.class, () -> {
      TruffulaOptions options = new TruffulaOptions(args);
    });
  }

  @Test
  void testValidArgsExceeds(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-h", "-h", "-nc", directoryPath};

    assertThrows(IllegalArgumentException.class, () -> {
      TruffulaOptions options = new TruffulaOptions(args);
    });
  }

  @Test
  void testValidArgsPathMissing(@TempDir File tempDir) throws FileNotFoundException {
    String[] args = {"-h", "-nc"};

    assertThrows(IllegalArgumentException.class, () -> {
      TruffulaOptions options = new TruffulaOptions(args);
    });
  }

  @Test
  void testValidArgsPathMissingUnordered(@TempDir File tempDir) throws FileNotFoundException {
    String[] args = {"-nc", "-h"};

    assertThrows(IllegalArgumentException.class, () -> {
      TruffulaOptions options = new TruffulaOptions(args);
    });
  }

  @Test
  void testValidArgsSingleFlagNoPath(@TempDir File tempDir) throws FileNotFoundException {
    String[] args = {"-nc"};

    assertThrows(IllegalArgumentException.class, () -> {
      TruffulaOptions options = new TruffulaOptions(args);
    });
  }

  @Test
  void testValidArgsPathNotDirectory(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-h", "-nc", directoryPath};

    assertThrows(FileNotFoundException.class, () -> {
      TruffulaOptions options = new TruffulaOptions(args);
    });
  }
}
