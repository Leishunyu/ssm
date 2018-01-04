package com.ssm.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class CryptoUtil {

    /**
     * RSA签名方式
     */
    private static final String RSA = "RSA";

    /**
     * 加密
     * 
     * @param plainBytes 明文字节数组
     * @param publicKey 公钥
     * @param keyLength 密钥bit长度
     * @param reserveSize padding填充字节数，预留11字节
     * @param cipherAlgorithm 加解密算法，一般为RSA/ECB/PKCS1Padding
     * @return 加密后字节数组，不经base64编码
     * @throws Exception
     */
    private static byte[] encrypt(byte[] plainBytes, PublicKey publicKey, int keyLength, int reserveSize,
            String cipherAlgorithm) throws Exception {
        int keyByteSize = keyLength / 8; // 密钥字节数
        int encryptBlockSize = keyByteSize - reserveSize; // 加密块大小=密钥字节数-padding填充字节数
        int nBlock = plainBytes.length / encryptBlockSize;// 计算分段加密的block数，向上取整
        if ((plainBytes.length % encryptBlockSize) != 0) { // 余数非0，block数再加1
            nBlock += 1;
        }

        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // 输出buffer，大小为nBlock个keyByteSize
            ByteArrayOutputStream outbuf = new ByteArrayOutputStream(nBlock * keyByteSize);
            // 分段加密
            for (int offset = 0; offset < plainBytes.length; offset += encryptBlockSize) {
                System.out.println("offset=" + offset);
                int inputLen = plainBytes.length - offset;
                if (inputLen > encryptBlockSize) {
                    inputLen = encryptBlockSize;
                }

                // 得到分段加密结果
                byte[] encryptedBlock = cipher.doFinal(plainBytes, offset, inputLen);
                // 追加结果到输出buffer中
                outbuf.write(encryptedBlock);
            }

            outbuf.flush();
            outbuf.close();
            return outbuf.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(String.format("没有[%s]此类加密算法", cipherAlgorithm));
        } catch (NoSuchPaddingException e) {
            throw new Exception(String.format("没有[%s]此类填充模式", cipherAlgorithm));
        } catch (InvalidKeyException e) {
            throw new Exception("无效密钥");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("加密块大小不合法");
        } catch (BadPaddingException e) {
            throw new Exception("错误填充模式");
        } catch (IOException e) {
            throw new Exception("字节输出流异常");
        }
    }

    /**
     * 转为16进制 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param bcdhex
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static String bcdhexToAschex(byte[] bcdhex) {
        byte[] aschex = { 0, 0 };
        String res = "";
        String tmp = "";
        for (int i = 0; i < bcdhex.length; i++) {
            aschex[1] = hexLowToAsc(bcdhex[i]);
            aschex[0] = hexHighToAsc(bcdhex[i]);
            tmp = new String(aschex);
            res += tmp;
        }
        return res;
    }

    private static byte hexLowToAsc(byte xxc) {
        xxc &= 0x0f;
        if (xxc < 0x0a)
            xxc += '0';
        else
            xxc += 0x37;
        return (byte) xxc;
    }

    private static byte hexHighToAsc(int xxc) {
        xxc &= 0xf0;
        xxc = xxc >> 4;
        if (xxc < 0x0a)
            xxc += '0';
        else
            xxc += 0x37;
        return (byte) xxc;
    }

    /**
     * 
     * 将通过Base64编码后的String类型的私钥字符串转换为PrivateKey对象
     * 
     * @param strPriKey Base64编码后的String类型的私钥字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @since 10.15
     */
    public static PrivateKey getPrivateKey(String strPriKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(strPriKey));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey priKey = keyFactory.generatePrivate(priKeySpec);
        return priKey;
    }

    /**
     * 加密 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param jsonStr
     * @return
     * @throws Exception 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String encryptJson(String jsonStr) throws Exception {
        CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
        FileInputStream bais = new FileInputStream(System.getProperty("user.dir")+"/yifubao-pre.cer");
        X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(bais);
        PublicKey publicKey = Cert.getPublicKey();
        byte[] encryptedData = CryptoUtil.encrypt(jsonStr.getBytes("UTF-8"), publicKey, 2048, 11, "RSA");// 加密
        System.out.println("加密串:" + bcdhexToAschex(encryptedData));
        return bcdhexToAschex(encryptedData);

    }

    /**
     * 
     * RSA签名
     * 
     * @param data 请求原文
     * @param prik 私钥
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String sign(String data, PrivateKey prik) throws InvalidKeyException, NoSuchAlgorithmException,
            SignatureException {
        return Base64.encodeBase64URLSafeString(sign(data.getBytes(), prik)).trim();
    }

    /**
     * 
     * RSA签名:
     * 
     * @param data 请求原文
     * @param prik 私钥
     * @return 签名值
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @since 10.15
     */
    public static byte[] sign(byte[] data, PrivateKey prik) throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException {
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(prik);
        signature.update(data);
        return signature.sign();
    }

    public static void main(String[] args) {
        try {

            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            FileInputStream bais = new FileInputStream(System.getProperty("user.dir")+"/yifubao-pre.cer");
            X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(bais);
            PublicKey pk = Cert.getPublicKey();
            System.out.println("pk:" + Base64.encodeBase64String(pk.getEncoded()));
            PublicKey publicKey = Cert.getPublicKey();
            String plainXML = "{\"cardHolderName\":\"黄晓明\",\"certNo\":\"330726196507040016\",\"mobileNo\":\"18651661234\",\"cardNo\":\"5316930130035039\",\"expYear\":\"18\",\"expMonth\":\"06\",\"cvv\":\"123\"}";
            byte[] encryptedData = CryptoUtil.encrypt(plainXML.getBytes("UTF-8"), publicKey, 2048, 11, "RSA");// 加密
            System.out.println("加密串:" + bcdhexToAschex(encryptedData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
