package meghanada.completion;

import static meghanada.config.Config.timeIt;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Collection;
import meghanada.GradleTestBase;
import meghanada.reflect.CandidateUnit;
import org.junit.Ignore;
import org.junit.Test;

public class JavaCompletionTest extends GradleTestBase {

  @Test
  public void testCompletion01() throws Exception {
    JavaCompletion completion = getCompletion();
    File file = new File("./src/test/java/meghanada/TopClass.java").getCanonicalFile();
    assert file.exists();
    final Collection<? extends CandidateUnit> units =
        timeIt(() -> completion.completionAt(file, 8, 9, "*this"));
    units.forEach(a -> System.out.println(a.getDeclaration()));
    assertEquals(15, units.size());
  }

  @Test
  public void testCompletion02() throws Exception {
    JavaCompletion completion = getCompletion();
    File file = new File("./src/test/java/meghanada/TopClass.java").getCanonicalFile();
    assert file.exists();
    final Collection<? extends CandidateUnit> units =
        timeIt(() -> completion.completionAt(file, 14, 9, "*this"));
    units.forEach(a -> System.out.println(a.getDeclaration()));
    assertEquals(15, units.size());
  }

  @Test
  public void testCompletion03() throws Exception {
    JavaCompletion completion = getCompletion();
    File file = new File("./src/test/java/meghanada/TopClass.java").getCanonicalFile();
    assert file.exists();

    final Collection<? extends CandidateUnit> units =
        timeIt(() -> completion.completionAt(file, 8, 9, "fo"));
    units.forEach(a -> System.out.println(a.getDeclaration()));
    assertEquals(1, units.size());
  }

  @Test
  public void testCompletion04() throws Exception {
    JavaCompletion completion = getCompletion();
    File file = new File("./src/test/java/meghanada/TopClass.java").getCanonicalFile();
    assert file.exists();

    final Collection<? extends CandidateUnit> units =
        timeIt(() -> completion.completionAt(file, 8, 9, "@Test"));
    units.forEach(a -> System.out.println(a.getDeclaration()));
    assertEquals(6, units.size());
  }

  @Test
  public void testCompletion05() throws Exception {
    JavaCompletion completion = getCompletion();
    File file =
        new File("./src/main/java/meghanada/analyze/ExpressionScope.java").getCanonicalFile();
    assert file.exists();
    final Collection<? extends CandidateUnit> staticLog =
        timeIt(() -> completion.completionAt(file, 16, 4, "lo"));
    staticLog.forEach(a -> System.out.println(a.getDeclaration()));
    assertEquals(staticLog.size(), 1);

    final Collection<? extends CandidateUnit> pos =
        timeIt(() -> completion.completionAt(file, 16, 8, "po"));
    pos.forEach(a -> System.out.println(a.getDeclaration()));
    assertEquals(pos.size(), 1);
  }

  @Test
  public void testCompletion06() throws Exception {
    JavaCompletion completion = getCompletion();
    File file =
        new File("./src/main/java/meghanada/analyze/ExpressionScope.java").getCanonicalFile();
    assert file.exists();
    final Collection<? extends CandidateUnit> logMethod =
        timeIt(() -> completion.completionAt(file, 18, 4, "*log#"));
    logMethod.forEach(a -> System.out.println(a.getDeclaration()));
    assertEquals(369, logMethod.size());
  }

  @Test
  public void testCompletion07() throws Exception {
    JavaCompletion completion = getCompletion();
    File file =
        new File("./src/main/java/meghanada/analyze/ExpressionScope.java").getCanonicalFile();
    assert file.exists();

    final Collection<? extends CandidateUnit> logMethod =
        timeIt(() -> completion.completionAt(file, 17, 4, "*method:java.lang.System#"));
    logMethod.forEach(a -> System.out.println(a.getDeclaration()));
    assertEquals(logMethod.size(), 39);
  }

  @Test
  public void testCompletion08() throws Exception {
    JavaCompletion completion = getCompletion();
    File file = new File("./src/main/java/meghanada/analyze/JavaAnalyzer.java").getCanonicalFile();
    assert file.exists();

    final Collection<? extends CandidateUnit> units =
        timeIt(
            () ->
                completion.completionAt(
                    file,
                    79,
                    35,
                    "*method:java.util.Iterator<capture of ? extends com.sun.source.tree.CompilationUnitTree>#"));
    // units.forEach(a -> System.out.println(a.getDisplayDeclaration()));
    assertEquals(13, units.size());
    for (CandidateUnit unit : units) {
      if (unit.getName().equals("next")) {
        final String returnType = unit.getReturnType();
        assertEquals("capture of ? extends com.sun.source.tree.CompilationUnitTree", returnType);
      }
    }
  }

  @Test
  public void testCompletion09() throws Exception {
    JavaCompletion completion = getCompletion();
    File file = new File("./src/main/java/meghanada/analyze/JavaAnalyzer.java").getCanonicalFile();
    assert file.exists();
    final Collection<? extends CandidateUnit> units =
        timeIt(
            () ->
                completion.completionAt(
                    file,
                    79,
                    35,
                    "*method:capture of ? extends com.sun.source.tree.CompilationUnitTree#"));
    units.forEach(a -> System.out.println(a.getDisplayDeclaration()));
    assertEquals(17, units.size());
  }

  @Ignore
  @Test
  public void testCompletion10() throws Exception {
    JavaCompletion completion = getCompletion();
    File file = new File("./src/main/java/meghanada/analyze/JavaAnalyzer.java").getCanonicalFile();
    assert file.exists();
    final Collection<? extends CandidateUnit> units =
        timeIt(() -> completion.completionAt(file, 79, 35, "Dia"));
    units.forEach(a -> System.out.println(a.getDisplayDeclaration()));
    assertEquals(2329, units.size());
  }

  private JavaCompletion getCompletion() throws Exception {
    return new JavaCompletion(getProject());
  }
}
