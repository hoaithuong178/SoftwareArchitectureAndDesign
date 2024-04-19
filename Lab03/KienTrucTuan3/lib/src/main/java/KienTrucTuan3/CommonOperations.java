package KienTrucTuan3;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CommonOperations {

	/**
	 * Ensure that the package name starts with the syntax "com.companyname.".
	 */
	public static void syntaxPackage(File projectDir) {
		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			try {
				new VoidVisitorAdapter<Object>() {
					@Override
					public void visit(PackageDeclaration n, Object arg) {
						super.visit(n, arg);  // Call to super method is not necessary but can be maintained for full visitor functionality
						Pattern pattern = Pattern.compile("\\bcom\\.companyname\\.", Pattern.CASE_INSENSITIVE);
						Matcher matcher = pattern.matcher(n.getNameAsString());
						if (!matcher.find()) {
							System.out.println("Package " + n.getNameAsString() + " does not begin with 'com.companyname.'");
						}
					}
				}.visit(StaticJavaParser.parse(file), null);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).explore(projectDir);
	}
}
