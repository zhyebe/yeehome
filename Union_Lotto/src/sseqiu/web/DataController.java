package sseqiu.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sseqiu.entity.UnionLotto;

@Controller
@RequestMapping("/data")
public class DataController {
	
	/*要抓取数据的网址*/
	private static final String WEB_URL="http://zx.500.com/ssq/";
	
	@RequestMapping("/getWebData.do")
	@ResponseBody
	public UnionLotto getWebHtml() throws IOException{
		//获取文档对象
		Document doc=Jsoup.connect(WEB_URL).timeout(10000).get();
		Element element=doc.getElementById("kj_opencode");
		Element elementId=doc.getElementById("kj_expect");
		Elements elementIds=elementId.children();
		Elements redballElements=element.getElementsByClass("redball");
		Elements blueballElements=element.getElementsByClass("blueball");
		List<String> redBalls=new ArrayList<String>();
		for(Element e:redballElements){
			redBalls.add(e.text());
		}
		String blueBall=blueballElements.get(0).text();
		int id=Integer.parseInt(elementIds.get(0).val());
		UnionLotto lotto=new UnionLotto();
		lotto.setId(id);
		lotto.setRedBalls(redBalls);
		lotto.setBlueBall(blueBall);
		return lotto;
	}
	
}
