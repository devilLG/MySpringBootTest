package com.zhilai.master.modules.zxml;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.modules.parmter.entity.MsgSend;

/**
 * 短信发送
 * @author zzw
 *
 */
public class MessageSendReq extends ClientReqHead {
	private final static Logger log = Logger.getLogger(MessageSendReq.class);
	private MsgSend msgSend;
	private String xml;

	@Override
	public String CreateXml() {
		try {
			Document document = super.getDocument();
			Element root = document.getRootElement();
			Element zbody = root.addElement("ZBODY");
			Element e = zbody.addElement("auth_name");
			e.setText(this.auth_name == null ? " " : this.auth_name);
			e = zbody.addElement("auth_id");
			e.setText(this.auth_id == null ? " " : this.auth_id);
			e = zbody.addElement("busi_type");
			e.setText(msgSend.getBusi_type() == null ? " " : msgSend.getBusi_type());
			e = zbody.addElement("mobile");
			e.setText(msgSend.getNotice_way() == null ? "13410310002" : msgSend.getNotice_way());
			e = zbody.addElement("cont");
			e.setText(msgSend.getCont() == null ? " " :msgSend.getCont());
			e = zbody.addElement("plan_time");
			e.setText(msgSend.getPlan_time() == null ? DateUtils.DateToString() :msgSend.getPlan_time());
			e = zbody.addElement("create_time");
			e.setText(msgSend.getCreate_time() == null ? DateUtils.DateToString() :msgSend.getCreate_time());
			e = zbody.addElement("level");
			e.setText(msgSend.getLevel() == null ? " " : msgSend.getLevel());

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OutputFormat format = new OutputFormat("  ", true,
					this.getCharSet());
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			xml = out.toString(this.getCharSet());
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return null;
		}
		return xml;
	}

	public MsgSend getMsgSend() {
		return msgSend;
	}

	public void setMsgSend(MsgSend msgSend) {
		this.msgSend = msgSend;
	}

}
