//package javaparser;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import com.github.javaparser.JavaParser;
//import com.github.javaparser.ParseResult;
//import com.github.javaparser.ast.CompilationUnit;
//import com.github.javaparser.ast.body.FieldDeclaration;
//import com.github.javaparser.ast.body.MethodDeclaration;
//
//public class VoidVisitorStarted {
//	private static final String filePath="src/main/java/javaparser/ReversePolishNotation.java";
//
//	public static void main(String[] args) throws Exception{
//		ParseResult<CompilationUnit> parseResult=null;
//		FileInputStream in =new FileInputStream(filePath);
//		try {
//			JavaParser parser=new JavaParser();
//			parseResult=parser.parse(in);
//			Optional<CompilationUnit> optinal=parseResult.getResult();
//			if(optinal.isPresent()) {
//				CompilationUnit cu=optinal.get();
//				getFields(cu);
//				System.out.println("=========================================");
//				getMethods(cu);
//				System.out.println("=========================================");
//				List<String> a=getFileInFolder("src/main/java/javaparser");
//				for (String string : a) {
//					System.out.println(string);
//				}
//			}
//		} finally {
//			in.close();
//		}
//	}
//	public static List<String> getFileInFolder(String folderName) {
//		File folder=new File(folderName);
//		File [] files=folder.listFiles();
//		List<String> fileName=new ArrayList<>();
//		for (File file : files) {
//			if(file.isDirectory()) getFileInFolder(file.getName());
//			fileName.add(file.getName());
//		}
//		return fileName;
//	}
//	public static void getMethods(CompilationUnit cu) {
//		List<MethodDeclaration> methods=cu.findAll(MethodDeclaration.class);
//		for (MethodDeclaration methodDeclaration : methods) {
//			System.out.println(methodDeclaration.getName());
//		}
//	}
//	public static void getFields(CompilationUnit cu) {
//		List<FieldDeclaration> fields=cu.findAll(FieldDeclaration.class);
//		for (FieldDeclaration fieldDeclaration : fields) {
//			System.out.println(fieldDeclaration);
//		}
//	}
//
//}
package javaparser;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import javaparser.code.Person;
import javaparser.code.UtilityClass;

public class VoidVisitorStarted {

	public static void main(String[] args) {
		String classPath = "src/main/java/javaparser/ReversePolishNotation.java";
		System.out.println("============ FILE ===============");
		parseFile(new File(classPath));

		String directoryPath = "src/main/java/javaparser/code";
		System.out.println("============ FOLDER ===============");
		readFolder(new File(directoryPath));

		System.out.println("============ Reflection ===============");
		reflection(Person.class);
	}

	private static void readFolder(File folderName) {
		List<File> javaFiles = new ArrayList<>();
		getAllJavaFiles(folderName, javaFiles);

		javaFiles.forEach(VoidVisitorStarted::parseFile);
	}

	private static void parseFile(File javaFile) {
		JavaParser parser = new JavaParser();
		try (FileInputStream in = new FileInputStream(javaFile)) {
			ParseResult<CompilationUnit> parseResult = parser.parse(in);
			parseResult.getResult().ifPresent(cu -> {
				System.out.println("File: " + javaFile.getName());
				getFields(cu);
				System.out.println("=========================================");
				getMethods(cu);
				System.out.println("=========================================");
			});
		} catch (Exception e) {
			System.err.println("Error processing file " + javaFile.getName() + ": " + e.getMessage());
		}
	}

	private static void getAllJavaFiles(File dir, List<File> javaFiles) {
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					getAllJavaFiles(file, javaFiles);
				} else if (file.getName().endsWith(".java")) {
					javaFiles.add(file);
				}
			}
		}
	}

	private static void getMethods(CompilationUnit cu) {
		List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
		for (MethodDeclaration method : methods) {
			System.out.println("Method: " + method.getSignature());
		}
	}

	private static void getFields(CompilationUnit cu) {
		List<FieldDeclaration> fields = cu.findAll(FieldDeclaration.class);
		for (FieldDeclaration field : fields) {
			for (VariableDeclarator var : field.getVariables()) {
				System.out.println("Field: " + var.getType() + " " + var.getName());
			}
		}
	}

	private static void reflection(Class<?> clazz) {
		System.out.println("Fields:");
		for (Field field : clazz.getDeclaredFields()) {
			System.out.println(field.getName() + " : " + field.getType());
		}
		System.out.println("Methods:");
		for (Method method : clazz.getDeclaredMethods()) {
			System.out.println(method.getName() + " : " + method.getReturnType() + " with parameters " + java.util.Arrays.toString(method.getParameterTypes()));
		}
	}
}
