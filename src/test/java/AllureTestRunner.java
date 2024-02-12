/*****************************************************************
 * Vip Service http://www.vipservice.ru
 * Project: webtests
 *
 * $Id: $
 *****************************************************************/

import io.qameta.allure.Allure;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class AllureTestRunner {

    private static final Path FILE_TESTS_NAMES = Paths.get("./tests_names.txt");

//    private static final String TESTS_PACKAGE_NAME =
//            "portbilet";

//    private static class TestMethod {
//
//        private final Class<?> clazz;
//
//        private final Method method;
//
//        private final int priority;
//
//        private final String displayName;
//
//        private boolean isScheduled = false;
//
//        TestMethod(Method method) {
//            this.method = method;
//            this.clazz = method.getDeclaringClass();
//            if (this.method.isAnnotationPresent(DisplayName.class)) {
//                displayName =
//                        this.method.getAnnotation(DisplayName.class).value();
//            } else {
//                displayName = null;
//            }
//            if (this.method.isAnnotationPresent(TestOrder.class)) {
//                priority =
//                        this.method.getAnnotation(TestOrder.class).priority();
//            } else if (this.clazz.isAnnotationPresent(TestOrder.class)) {
//                priority = this.clazz.getAnnotation(TestOrder.class).priority();
//            } else {
//                priority = Integer.MAX_VALUE / 2;
//            }
//        }
//
//        void setScheduled() {
//            isScheduled = true;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (o == null || getClass() != o.getClass()) {
//                return false;
//            }
//            TestMethod that = (TestMethod) o;
//            return method.equals(that.method);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(method);
//        }
//    }

    public static void main(final String[] args) throws Exception {
//        List<TestMethod> checkedBySetConfigurationTests = loadTestsFromPackage()
//                .stream().filter(m -> m.isAnnotationPresent(Test.class))
//                .map(TestMethod::new)
//                .filter(AllureTestRunner::checkTestMatchConfiguration)
//                .collect(Collectors.toList());
//        System.out.println("total number of tests: " + checkedBySetConfigurationTests.size());

        Set<String> testSuites = new HashSet<>();
        Set<String> systemArg = Arrays.stream(args).collect(Collectors.toSet());
        systemArg.remove("${suites}");
        systemArg.remove("${tests}");
//        if (systemArg.isEmpty()) {
//            for (TestMethod metod : checkedBySetConfigurationTests) {
//                testSuites.add(metod.clazz.getName() + "::" + metod.method.getName());
//            }
//        } else {
//            testSuites.addAll(systemArg);
//        }
        if ("true".equals(System.getProperty("devmode.deleteBuildFolder"))) {
            Path buildFolder = Paths.get("./build");
            if (Files.exists(buildFolder)) {
                Files.walkFileTree(buildFolder, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(final Path file,
                                                     final BasicFileAttributes attrs)
                            throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(final Path dir,
                                                              final IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
                System.out.println(String.format("Folder %s deleted",
                        buildFolder.toAbsolutePath()));
            }
        }

//        ExecutorService testsExecutor = Executors.newFixedThreadPool(TestsConfig.ggrCore);
//        ConsoleTestListener consoleListener = new ConsoleTestListener();

        /*
         this synchronization is required to create one
         and only one instance of the public class Allure Storage
         otherwise, each thread creates its own Allure Storage
        */
        synchronized (AllureTestRunner.class) {
            Allure.getLifecycle();
        }

//        ThreadLocal<JUnitCore> junitCoreTl = new ThreadLocal<JUnitCore>() {
//            @Override
//            protected JUnitCore initialValue() {
//                JUnitCore jUnit = new JUnitCore();
//                jUnit.addListener(consoleListener);
//                jUnit.addListener(new XMLTestListener());
//                jUnit.addListener(new AllureJunit4());
//                return jUnit;
//            }
//        };
//        printServersConfigVariables();
//        printTestsConfigVariables();

//        for (String nameSuites : testSuites) {
////        Arrays.stream(testSuites).forEach(nameSuites -> {
//            String name = nameSuites
//                    .replace("Test.", "Test::")
//                    .replace("Test#", "Test::");
//            int sepIdx = name.indexOf("::");
//            String className;
//            if (sepIdx == -1) {
//                className = name;
//            } else {
//                className = name.substring(0, sepIdx);
//            }
//            final Class<?> clazz;
//            try {
//                clazz = Class.forName(className);
//            } catch (Exception e) {
//                throw new IllegalArgumentException(
//                        String.format("Invalid class name: \"%s\"", className), e);
//            }
//            if (sepIdx != -1) {
//                String methodName = name.substring(sepIdx + 2).trim();
//                Method testMethod;
//                try {
//                    testMethod = clazz.getMethod(methodName);
//                } catch (NoSuchMethodException e) {
//                    throw new IllegalStateException(String.format(
//                            "Class %s does not have method %s without arguments",
//                            clazz.getName(), methodName));
//                } catch (SecurityException e) {
//                    throw new IllegalStateException(e);
//                }
//                if (!testMethod.isAnnotationPresent(Test.class)) {
//                    throw new IllegalStateException(String.format(
//                            "Method %s::%s does not have @Test annotation.",
//                            clazz.getName(), methodName));
//                }
//                int testIdx = checkedBySetConfigurationTests.indexOf(new TestMethod(testMethod));
//                if (testIdx != -1) {
//                    checkedBySetConfigurationTests.get(testIdx).setScheduled();
//                }
//            } else {
//                final Request classRequest = new ClassRequest(clazz);
//                @SuppressWarnings("unchecked")
//                ParentRunner<FrameworkMethod> runner =
//                        (ParentRunner<FrameworkMethod>) classRequest.getRunner();
//                for (FrameworkMethod fm : runner.getTestClass()
//                        .getAnnotatedMethods()) {
//                    int testIdx = checkedBySetConfigurationTests.indexOf(new TestMethod(fm.getMethod()));
//                    if (testIdx != -1) {
//                        checkedBySetConfigurationTests.get(testIdx).setScheduled();
//                    }
//                }
//            }
//        }
//
//        checkedBySetConfigurationTests.stream().filter(t -> t.isScheduled)
//                .sorted(Comparator.comparingInt(t1 -> t1.priority))
//                .forEach(t -> submitTestMethod(testsExecutor, junitCoreTl, t.clazz,
//                        t.method.getName()));
//
//        saveTestsNamesInWorkspace(checkedBySetConfigurationTests);
//        testsExecutor.shutdown();
//        testsExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
//        XMLTestListener.writeAllTestsData();
    }

//    private static void printServersConfigVariables() {
//        System.out.println("CONFIG SERVER GGR host: " + TestsConfig.ggrBalancer);
//        System.out.println("CONFIG SERVER GGR port: " + TestsConfig.ggrPort);
//    }
//
//    @SuppressWarnings("nls")
//    private static void printTestsConfigVariables() {
//        System.out.println("CONFIG websiteUrl: " + TestsConfig.websiteUrl);
//        System.out
//                .println("CONFIG environmentName: " + TestsConfig.environmentName);
//        System.out
//                .println("CONFIG environmentType: " + TestsConfig.environmentType);
//        System.out.println("CONFIG functionalitiesIncluded: "
//                + TestsConfig.functionalitiesIncluded);
//        System.out.println(
//                "CONFIG aviaSearchOffsetDays: " + TestsConfig.aviaSearchOffsetDays);
//        System.out.println(
//                "CONFIG aviaUnsupportedGDSes: " + TestsConfig.aviaUnsupportedGDSes);
//    }
//
//    private static final void submitTestMethod(
//            final ExecutorService testsExecutor,
//            final ThreadLocal<JUnitCore> junitCoreTl, final Class<?> clazz,
//            final String methodName) {
//        testsExecutor.submit(() -> {
//            junitCoreTl.get().run(Request.method(clazz, methodName));
//        });
//        System.out.println(String.format("Test method %s::%s scheduled",
//                clazz.getName(), methodName));
//    }
//
//    private static boolean checkTestMatchConfiguration(final TestMethod test) {
//        Method testMethod = test.method;
//        String methodName = testMethod.getName();
//        Class<?> clazz = test.clazz;
//
//        if (TestsConfig.environmentType != DEVELOPMENT) {
//            EnvironmentType envTypeAnnotation =
//                    testMethod.getAnnotation(EnvironmentType.class);
//            if (envTypeAnnotation == null) {
////                System.out.println(String.format(
////                        "Test method %s::%s does not have @EnvironmentType annotation. Skipping it.",
////                        clazz.getName(), methodName));
//                return false;
//            }
//            Set<TestEnvironmentType> envTypes =
//                    EnumSet.noneOf(TestEnvironmentType.class);
//            for (TestEnvironmentType t : envTypeAnnotation.value()) {
//                envTypes.add(t);
//            }
//            if (!envTypes.contains(TestsConfig.environmentType)) {
////                System.out.println(String.format(
////                        "Test method %s::%s does not have @EnvironmentType.%s annotation. Skipping it.",
////                        clazz.getName(), methodName, TestsConfig.environmentType));
//                return false;
//            }
//        }
//
//        WebsiteFunctionality[] functionalities;
//        fCheck:
//        {
//            Functionality funcAnnotation =
//                    testMethod.getAnnotation(Functionality.class);
//            if (funcAnnotation != null) {
//                functionalities = funcAnnotation.value();
//                break fCheck;
//            }
//            funcAnnotation = clazz.getAnnotation(Functionality.class);
//            if (funcAnnotation != null) {
//                functionalities = funcAnnotation.value();
//                break fCheck;
//            }
//            functionalities = null;
//        }
//        if (TestsConfig.functionalitiesIncluded != null) {
//            check:
//            {
//                if (functionalities != null) {
//                    for (WebsiteFunctionality wf : functionalities) {
//                        if (TestsConfig.functionalitiesIncluded.contains(wf)) {
//                            break check;
//                        }
//                    }
//                }
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Looking for all the fully qualified names of the tests in the folder.
//     */
//    private static List<Method> loadTestsFromPackage() {
//        final List<Method> allTests = new ArrayList<>();
//        final ClassLoader classLoader =
//                Thread.currentThread().getContextClassLoader();
//        ClassPath classpath;
//        try {
//            classpath = ClassPath.from(classLoader);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new IllegalArgumentException(
//                    "Unable to load path from classLoader");
//        }
//        classpath.getTopLevelClassesRecursive(TESTS_PACKAGE_NAME).forEach(
//                c -> allTests.addAll(Arrays.asList(c.load().getMethods())));
//        return allTests;
//    }
//
//    private static void saveTestsNamesInWorkspace(
//            final List<TestMethod> tests) {
//        try (BufferedWriter out = Files.newBufferedWriter(FILE_TESTS_NAMES,
//                StandardCharsets.UTF_8, StandardOpenOption.CREATE,
//                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
//            for (TestMethod t : tests) {
//                out.write(t.displayName);
//                out.write("__");
//                out.write(t.clazz.getName());
//                out.write("::");
//                out.write(t.method.getName());
//                out.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new IllegalArgumentException("Unable to save names of tests");
//        }
//    }
}
