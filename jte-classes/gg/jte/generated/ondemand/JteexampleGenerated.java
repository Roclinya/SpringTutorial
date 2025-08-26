package gg.jte.generated.ondemand;
import java.util.HashMap;
import com.tutorial.SpringTutorial.vo.StockDto;
public final class JteexampleGenerated {
	public static final String JTE_NAME = "example.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,8,8,8,9,9,9,9,9,9,9,9,9,10,10,11,11,11,12,13,16,16,17,17,17,18,18,23,23,23,23,23,24,24,24,24,24,25,25,25,25,25,26,26,26,26,26,30,30,30,2,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, StockDto stockDto, HashMap<String, Object> global) {
		jteOutput.writeContent("\n<head>\n    ");
		if (global.get("year") != null) {
			jteOutput.writeContent("\n        <meta name=\"description\"");
			var __jte_html_attribute_0 = global.get("year").toString();
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
				jteOutput.writeContent(" content=\"");
				jteOutput.setContext("meta", "content");
				jteOutput.writeUserContent(__jte_html_attribute_0);
				jteOutput.setContext("meta", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent(">\n    ");
		}
		jteOutput.writeContent("\n    <title>Title ");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(stockDto.name);
		jteOutput.writeContent("!</title>\n    ");
		jteOutput.writeContent("\n");
		jteOutput.writeContent("\n</head>\n<body>\n");
		if (global.get("year") != null) {
			jteOutput.writeContent("\n    <h1>");
			jteOutput.setContext("h1", null);
			jteOutput.writeUserContent(global.get("year").toString());
			jteOutput.writeContent("</h1>\n");
		}
		jteOutput.writeContent("\n\n    <p>Welcome to my example page!</p>\n\n<select id=\"cars\">\n    <option value=\"volvo\"");
		var __jte_html_attribute_1 = false;
		if (__jte_html_attribute_1) {
		jteOutput.writeContent(" selected");
		}
		jteOutput.writeContent(">Volvo</option>\n    <option value=\"saab\"");
		var __jte_html_attribute_2 = true;
		if (__jte_html_attribute_2) {
		jteOutput.writeContent(" selected");
		}
		jteOutput.writeContent(">Saab</option>\n    <option value=\"opel\"");
		var __jte_html_attribute_3 = false;
		if (__jte_html_attribute_3) {
		jteOutput.writeContent(" selected");
		}
		jteOutput.writeContent(">Opel</option>\n    <option value=\"audi\"");
		var __jte_html_attribute_4 = false;
		if (__jte_html_attribute_4) {
		jteOutput.writeContent(" selected");
		}
		jteOutput.writeContent(">Audi</option>\n</select>\n</body>\">Audi</option>\n</select>\n</body>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		StockDto stockDto = (StockDto)params.get("stockDto");
		HashMap<String, Object> global = (HashMap<String, Object>)params.get("global");
		render(jteOutput, jteHtmlInterceptor, stockDto, global);
	}
}
