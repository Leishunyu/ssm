/**
 * Test.java
 */
package ssm;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.net.util.Base64;

import com.suning.epps.merchantsignature.common.RSAUtil;

/**
 * @author wywl
 * Jan 3, 2018 3:31:05 PM
 * modified by : 
 */
public class Test {
public static void main(String[] args) throws Exception {
	RSAUtil.createKey(System.getProperty("user.dir")+"/rsa_public_key.pem", System.getProperty("user.dir")+"/rsa_private_key.pem", 1024);
    // 转换为公钥对象
    PublicKey pubKey = RSAUtil.resolvePublicKey(System.getProperty("user.dir")+"/rsa_public_key.pem");
    // 转换为私钥对象
    PrivateKey priKey = RSAUtil.resolvePrivateKey(System.getProperty("user.dir")+"/rsa_private_key.pem");
    // Base64编码后的公钥字符串
    System.out.println("公钥：" + Base64.encodeBase64String(pubKey.getEncoded()));
    // Base64编码后的私钥字符串
    System.out.println("私钥：" + Base64.encodeBase64String(priKey.getEncoded()));
    String data = "B64DC35297E509D8078FDD64DDBBED73";
    // RSA加签
    String signData = RSAUtil.sign(data, priKey);
    System.out.println("签名值为：" + signData);
    // RSA验签
    boolean result =RSAUtil.vertiy(data, signData, pubKey);
    System.out.println("验签结果为：" + result);

}
}
