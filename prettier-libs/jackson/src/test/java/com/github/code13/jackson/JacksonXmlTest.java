package com.github.code13.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Code13
 * @date 2021-02-09 09:32
 */
public class JacksonXmlTest {


  String xml = "<sysmsg type=\"sysmsgtemplate\">\n" +
    "  <sysmsgtemplate>\n" +
    "    <content_template type=\"tmpl_type_profile\">\n" +
    "      <plain><![CDATA[]]></plain>\n" +
    "      <template><![CDATA[\"$username$\"邀请\"$names$\"加入了群聊]]></template>\n" +
    "      <link_list>\n" +
    "        <link name=\"username\" type=\"link_profile\">\n" +
    "          <memberlist>\n" +
    "            <member>\n" +
    "              <username><![CDATA[wxid_8rfrvi721nnb22]]></username>\n" +
    "              <nickname><![CDATA[糯米糍\uD83C\uDF65]]></nickname>\n" +
    "            </member>\n" +
    "          </memberlist>\n" +
    "        </link>\n" +
    "        <link name=\"names\" type=\"link_profile\">\n" +
    "          <memberlist>\n" +
    "            <member>\n" +
    "              <username><![CDATA[wxid_1xv8xhsu08kx21]]></username>\n" +
    "              <nickname><![CDATA[dreamslink]]></nickname>\n" +
    "            </member>\n" +
    "            <member>\n" +
    "              <username><![CDATA[wxid_c1dpfnpr91nb22]]></username>\n" +
    "              <nickname><![CDATA[王晶晶]]></nickname>\n" +
    "            </member>\n" +
    "          </memberlist>\n" +
    "          <separator><![CDATA[、]]></separator>\n" +
    "        </link>\n" +
    "      </link_list>\n" +
    "    </content_template>\n" +
    "  </sysmsgtemplate>\n" +
    "</sysmsg>";

  @Test
  public void name() throws IOException {
    XmlMapper xmlMapper = new XmlMapper();

    SysMsg sysMsg = xmlMapper.readValue(new File("D:\\code13\\own\\java-own\\prettier-libs\\jackson\\src\\test\\java\\com\\github\\code13\\jackson\\test.xml"), SysMsg.class);

    System.out.println(sysMsg);

  }

  @Test
  public void test1() throws IOException {
    XmlMapper xmlMapper = new XmlMapper();

    SysMsg sysMsg = xmlMapper.readValue(this.xml, SysMsg.class);

    System.out.println(sysMsg);

  }

}

@Data
@JacksonXmlRootElement(localName = "sysmsg")
class SysMsg {

  @JacksonXmlProperty(isAttribute = true, localName = "type")
  private String type;

  @JacksonXmlProperty(localName = "sysmsgtemplate")
  private SysMsgTemplate sysMsgTemplate;

}

@Data
class SysMsgTemplate {

  @JacksonXmlProperty(localName = "content_template")
  private ContentTemplate contentTemplate;

}

@Data
class ContentTemplate {

  @JacksonXmlProperty(isAttribute = true, localName = "type")
  private String type;

  private String plain;

  private String template;

  @JacksonXmlElementWrapper(localName = "link_list")
  @JacksonXmlProperty(localName = "link")
  private List<Link> linkList;

}

@Data
class Link {

  @JacksonXmlProperty(isAttribute = true, localName = "name")
  private String name;

  @JacksonXmlProperty(isAttribute = true, localName = "type")
  private String type;

  @JacksonXmlElementWrapper(localName = "memberlist")
  @JacksonXmlProperty(localName = "member")
  private List<Member> memberList;

  private String separator;

}

@Data
class Member {

  @JacksonXmlProperty(localName = "username")
  private String userName;

  @JacksonXmlProperty(localName = "nickname")
  private String nickName;

}
