package com.caiyunfei.cyf.third.wechat;


import com.caiyunfei.cyf.third.wxpay.WXPayUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WechatPayUtil {



	// public final static String appidGZH = "";// 公众号支付 appid
	// public final static String secretGZH = "";// 公众号支付

	// appid
	private  static String appid="wx7b2dd480cefb9999";// APP支付 appid
	public final static String mch_id = "1516959151";
	//https://app.ustreetapp.com/
	private  static String notify_uri="https://app.ustreetapp.com/api/callback/wxpay";
	public final static String key = "qwertYuiopasdfghjkLZxcvbnm950523";
	public final static String PACKAGE = "Sign=WXPay";
	public final static String HMACSHA256 = "HMAC-SHA256";
	private final static String wxpay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private final static String wxpay_query = "https://api.mch.weixin.qq.com/pay/orderquery";
	private final static String wxpay_refund = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	public static Map<String, String> getUnionPayInfoMap(String appid,
														 String trade_type, String sn, Long fee, String sdesc, String ip,
														 int timeout) {
		Map<String, String> r = new HashMap<String, String>();
		r.put("appid", appid);
		r.put("mch_id", mch_id);
		String nonce_str = WXPayUtil.generateNonceStr();
		r.put("nonce_str", nonce_str);
		r.put("out_trade_no", sn);
		r.put("total_fee", String.valueOf(fee * 100));
		r.put("notify_url", notify_uri);
		r.put("trade_type", trade_type);
		r.put("body", sdesc);
		r.put("spbill_create_ip", ip);
		return r;
	}

	public static Map unifiedorderAPP(String sn, Long fee, String sdesc,
									  String ip, int timeout) throws Exception {
		Map<String, String> paymap = getUnionPayInfoMap(appid, "APP", sn, fee,
				sdesc, ip, timeout);
		String xml = WXPayUtil.generateSignedXml(paymap, key);
		return remoteGetUnifiedOrder(xml);
	}

	public static Map queryOrderApp(String orderCode) throws Exception {
		Map<String, String> paymap = new HashMap<String, String>();
		paymap.put("appid", appid);
		paymap.put("mch_id", mch_id);
		String nonce_str = WXPayUtil.generateNonceStr();
		paymap.put("nonce_str", nonce_str);
		paymap.put("out_trade_no", orderCode);
		String xml = WXPayUtil.generateSignedXml(paymap, key);
		return remoteQueryOrder(xml);
	}

	public static Map refundOrderApp(String orderCode, String outRefundNo, BigDecimal b1, BigDecimal b2) throws Exception {
		String totalFees = String.format("%.0f", b1.multiply(new BigDecimal(100)).doubleValue());

		String refundFees = String.format("%.0f", b2.multiply(new BigDecimal(100)).doubleValue());
		Map<String, String> paymap = new HashMap<String, String>();
		paymap.put("appid", appid);
		paymap.put("mch_id", mch_id);
		String nonce_str = WXPayUtil.generateNonceStr();
		paymap.put("nonce_str", nonce_str);
		paymap.put("out_trade_no", orderCode);
		paymap.put("out_refund_no", outRefundNo);
		paymap.put("total_fee", totalFees);
		paymap.put("refund_fee", refundFees);
		String xml = WXPayUtil.generateSignedXml(paymap, key);
		return refundOrder(xml);
	}

	public static Map<String, String> remoteGetUnifiedOrder(String xml)
			throws Exception {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		OkHttpClient client = builder.build();
		RequestBody body = RequestBody.create(
				okhttp3.MediaType.parse("text/xml"), xml);
		Request request = new Request.Builder().url(wxpay_url).post(body)
				.build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			byte[] bs = response.body().bytes();
			String r = new String(bs, "UTF-8");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docbuilder = factory.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(bs);
			Document document = docbuilder.parse(stream);
			Element e = document.getDocumentElement();
			NodeList nl = e.getChildNodes();
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < nl.getLength(); i++) {
				Node nd = nl.item(i);
				map.put(nd.getNodeName(), nd.getTextContent());
				// System.out.println(nd.getNodeName() + "=" +
				// nd.getTextContent());
			}
			String return_code = map.get("return_code");
			String paysn = null;
			if ("SUCCESS".equals(return_code)) {
				paysn = (map.get("prepay_id"));
			}
			if (paysn == null) {
				throw new Exception("微信查询失败" + r);
			}
			return map;
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {

				}
			}
		}
	}

	public static Map<String, String> remoteQueryOrder(String xml)
			throws Exception {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		OkHttpClient client = builder.build();
		RequestBody body = RequestBody.create(
				okhttp3.MediaType.parse("text/xml"), xml);
		Request request = new Request.Builder().url(wxpay_query).post(body)
				.build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			byte[] bs = response.body().bytes();
			String r = new String(bs, "UTF-8");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docbuilder = factory.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(bs);
			Document document = docbuilder.parse(stream);
			Element e = document.getDocumentElement();
			NodeList nl = e.getChildNodes();
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < nl.getLength(); i++) {
				Node nd = nl.item(i);
				map.put(nd.getNodeName(), nd.getTextContent());
			}
//			String return_code = map.get("return_code");
//			String tradeState = null;
			System.out.println(map);
//			if ("SUCCESS".equals(return_code)) {
//				tradeState = (map.get("trade_state"));
//			}
//			if (!tradeState.equalsIgnoreCase("SUCCESS"))
//				throw new Exception("微信支付失败" + r);
			return map;
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {

				}
			}
		}
	}

	public static Map<String, String> refundOrder(String xml) {
//		OkHttpClient.Builder builder = new OkHttpClient.Builder();
//		OkHttpClient client = builder.build();
//		RequestBody body = RequestBody.create(
//				okhttp3.MediaType.parse("text/xml"), xml);
//		Request request = new Request.Builder().url(wxpay_refund).post(body)
//				.build();
		Map<String, String> map = new HashMap<String, String>();
		try {
			String response = SSLClient.doRefund(wxpay_refund, xml);
//			response = client.newCall(request).execute();
//			byte[] bs = response.body().bytes();
//			String r = new String(bs, "UTF-8");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docbuilder = factory.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(response.getBytes());
			Document document = docbuilder.parse(stream);
			Element e = document.getDocumentElement();
			NodeList nl = e.getChildNodes();

			for (int i = 0; i < nl.getLength(); i++) {
				Node nd = nl.item(i);
				map.put(nd.getNodeName(), nd.getTextContent());
			}
			String return_code = map.get("return_code");
			String tradeState = null;
			if ("SUCCESS".equals(return_code)) {
				tradeState = (map.get("result_code"));
			}
			if (!tradeState.equalsIgnoreCase("SUCCESS"))
				throw new Exception("微信支付失败" );
			return map;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
//			return map;
//			if (response != null) {
//				try {
//					response.close();
//				} catch (Exception e) {
//
//				}
//			}
		}
		return map;
	}

}
