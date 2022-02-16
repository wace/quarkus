package org.readingisgood;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeIsAliveResourceIT extends IsAliveTest {
  // Execute the same tests but in native mode.
}
