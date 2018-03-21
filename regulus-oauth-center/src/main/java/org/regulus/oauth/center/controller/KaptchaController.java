package org.regulus.oauth.center.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.regulus.common.enums.ResponseCodeEnum;
import org.regulus.common.model.ResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value= "KaptchaController",tags ="验证码" ,description="验证码生成以及校验")
@RestController
public class KaptchaController {

	@Autowired
	private Producer kaptchaProducer;
	
    @ApiOperation(value = "获取验证码图片" ,notes="获取验证码图片")
	@RequestMapping(value="/kaptcha.jpg",method=RequestMethod.GET)
	public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setDateHeader("Expires", 0);

		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// create the text for the image
		String capText = kaptchaProducer.createText();

		// store the text in the session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		// create the image with the text
		BufferedImage bi = kaptchaProducer.createImage(capText);
		
        ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		
		try {
			out.flush();
		} catch (Exception e) {
			out.close();
		}
		return null;
	}
	
    @ApiOperation(value = "检验图片验证码" ,notes="检验图片验证码")
	@RequestMapping(value="/checkKaptcha",method=RequestMethod.GET)
	public ResponseJson<String> checkKaptcha(String clientCode,HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		String serverCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		if(clientCode != null && clientCode.equalsIgnoreCase(serverCode)){
			return new ResponseJson<String>(true, ResponseCodeEnum.REQUEST_SUCCESS);
		}else{
			return new ResponseJson<String>(false, ResponseCodeEnum.REQUEST_FAIL.getCode(),"验证码校验失败");
		}

	}
}
