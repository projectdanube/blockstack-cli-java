package com.danubetech.blockstore.client.jsonrpc.autogen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoGenFromPythonDef {

	static final Pattern PATTERN_LINE = Pattern.compile("^    def jsonrpc_([^(]+)\\( ?self([^)]*)\\):$");

	public static void main(String[] args) throws Exception {

		autogen("BlockstoreClient", "BasicBlockstoreClient");
		autogen("DHTClient", "BasicDHTMirrorClient");
	}

	public static void autogen(String I, String C) throws Exception {

		BufferedReader reader = new BufferedReader(new InputStreamReader(AutoGenFromPythonDef.class.getResourceAsStream(I + ".txt"), "UTF-8"));
		BufferedReader readerIH = new BufferedReader(new InputStreamReader(AutoGenFromPythonDef.class.getResourceAsStream(I + ".java.header"), "UTF-8"));
		BufferedReader readerIF = new BufferedReader(new InputStreamReader(AutoGenFromPythonDef.class.getResourceAsStream(I + ".java.footer"), "UTF-8"));
		BufferedReader readerCH = new BufferedReader(new InputStreamReader(AutoGenFromPythonDef.class.getResourceAsStream(C + ".java.header"), "UTF-8"));
		BufferedReader readerCF = new BufferedReader(new InputStreamReader(AutoGenFromPythonDef.class.getResourceAsStream(C + ".java.footer"), "UTF-8"));
		String line;

		PrintWriter writerI = new PrintWriter(new FileWriter(new File(I + ".java")));
		PrintWriter writerC = new PrintWriter(new FileWriter(new File(C + ".java")));

		while ((line = readerIH.readLine()) != null) writerI.println(line);
		while ((line = readerCH.readLine()) != null) writerC.println(line);

		while ((line = reader.readLine()) != null) {

			Matcher matcher = PATTERN_LINE.matcher(line);
			if (! matcher.matches()) throw new Exception("No match: " + line);
			String method = javaMethod(matcher.group(1));
			String[] params = javaParams(matcher);
			StringBuilder builderI = new StringBuilder();
			StringBuilder builderC = new StringBuilder();
			builderC.append("	@Override\n");
			builderI.append("	public " + hintReturnType(method) + " ");
			builderC.append("	public " + hintReturnType(method) + " ");
			builderI.append(method);
			builderC.append(method);
			builderI.append("(");
			builderC.append("(");
			for (int i=0; i<params.length; i++) {
				builderI.append(hintParamType(method, params[i]) + " ");
				builderC.append(hintParamType(method, params[i]) + " ");
				builderI.append(params[i]);
				builderC.append(params[i]);
				if (i+1 < params.length) builderI.append(", ");
				if (i+1 < params.length) builderC.append(", ");
			}
			builderI.append(") throws IOException;");
			builderC.append(") throws IOException {");
			builderC.append("\n");
			builderC.append("		return this.sendAndExpect" + hintReturnType(method) + "(");
			builderC.append("\"" + matcher.group(1) + "\", new Object[] { ");
			for (int i=0; i<params.length; i++) {
				builderC.append(params[i]);
				if (i+1 < params.length) builderC.append(", ");
			}
			builderC.append(" });\n");
			builderC.append("	}\n");
			writerI.println(builderI.toString());
			writerC.println(builderC.toString());
		}

		while ((line = readerIF.readLine()) != null) writerI.println(line);
		while ((line = readerCF.readLine()) != null) writerC.println(line);

		reader.close();
		readerIH.close();
		readerIF.close();
		readerCH.close();
		readerCF.close();

		writerI.flush();
		writerC.flush();
		writerI.close();
		writerC.close();
	}

	static String javaMethod(String method) {

		StringBuilder builder = new StringBuilder();

		for (int i=0; i<method.length(); i++) {

			if (method.charAt(i) == '_') builder.append(Character.toUpperCase(method.charAt(++i)));
			else builder.append(method.charAt(i));
		}

		return builder.toString();
	}

	static String[] javaParams(Matcher matcher) {

		String[] params;
		String group = matcher.group(2).replace(" ", "");
		if (group.length() > 0 && group.charAt(0) == ',') group = group.substring(1);
		params = group.isEmpty() ? new String[0] : group.split(",");

		return params;
	}

	static String hintReturnType(String method) {

		if ("getAllNames".equals(method)) return "JSONArray";
		if ("getNamesInNamespace".equals(method)) return "JSONArray";

		return "JSONObject";
	}

	static String hintParamType(String method, String param) {

		if ("keepdata".equals(param)) return "boolean";
		if ("offset".equals(param)) return "Integer";
		if ("count".equals(param)) return "Integer";

		return "String";
	}
}
