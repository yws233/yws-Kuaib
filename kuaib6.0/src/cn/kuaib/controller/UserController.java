package cn.kuaib.controller;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

import cn.kuaib.pojo.Ex;
import cn.kuaib.pojo.User;
import cn.kuaib.service.UserService;
import cn.kuaib.tools.Constants;
import cn.kuaib.tools.SimHash;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.pdfbox.PDFReader;
import org.pdfbox.cos.COSDocument;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录之后操作快编(Kuaib)系统
 * @author yws
 * copyright yws Kuaib(快编系统)
 * 严禁用于商业及其他转载或一切不经本人允许的复制等
 * 希望本项目能为您带来编写的便捷，做一点贡献
 * 如提出建议可以git关注或来信
 */
@Controller
@RequestMapping("/sys/user") //全部进入拦截器
public class UserController extends BaseController{
	private Logger logger = Logger.getLogger(UserController.class);
	
	@Resource
	private UserService userService;

	/*
	* 注销
	* */
	@RequestMapping(value = "loginout.html")
    public String loginOut(HttpSession session, HttpServletRequest request){
        //统计文件夹中文件的个数
        String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles" + File.separator);
        File file = new File(path);
        int num = getFileNum(file); //调用文件数目统计
        logger.info("文件夹的路径为>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + path);
        logger.info("num文件个数为：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + num);
        //避免文件太多堵塞服务器，文件个数每次超过1000个就进行删除
        if (num > 1000) {
            // 调用删除文件的方法
            deleteDir(file);
        }
        // 注销用户信息
	    session.removeAttribute(Constants.USER_SESSION);

	    //注销时，将所有的文本内容清空
        session.setAttribute(Constants.TEXT_TXT, "");
        session.setAttribute("rword", "");
        session.setAttribute("reptxt1", "");
        session.setAttribute("tip","");
        session.setAttribute("upath", "");
        session.setAttribute("upath", "");
        session.setAttribute(Constants.UPIC_PATH,""); //储存文件的路径注销
        session.setAttribute("upcontext", "");
        session.setAttribute("filename", ""); //注销文件名
        request.setAttribute("uploadFileError", ""); //注销文件错误提示
        session.setAttribute("filecount", ""); //注销时候清除文件个数显示
        session.setAttribute("extip", "");
        session.setAttribute("exExist","");

	    return "login";
    }
    /*
    * 统计上传文件的个数方法
    * */
    public static int getFileNum(File file){
        int count = 0;
        File[] files = file.listFiles();
        int len = files.length;
        for (int i = 0; i < len; i++){
            count ++;
            if (files[i].isDirectory()){
                File file1 = new File(files[i].getAbsolutePath());
                count--;
                count = count + getFileNum(file1);
            }
        }
        return count;
    }
    /*
    * 删除上传文件夹下的所有文件方法
    * */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir
                        (new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        if(dir.delete()) {
            System.out.println("目录已被删除！");
            return true;
        } else {
            System.out.println("目录删除失败！");
            return false;
        }
    }


    /*
    * 文件上传
    * */
    @RequestMapping(value = "addpic.html", method = RequestMethod.POST)
    public String addPic(HttpSession session, HttpServletRequest request,@ModelAttribute User user,
                           Model model, HttpServletResponse response,
                         @RequestParam(value = "upicss",required = false) MultipartFile multipartFile){
        logger.info("进入上传文件页面》》》》》》》》》》》》》》》》》");
        //每次文件上传之前先清除之前提示信息
        request.setAttribute("uploadFileError", ""); //清除文件错误提示
        session.setAttribute("tip", ""); //清除中文乱码则提醒

        File file = null;
        String upic = null;
        String oldFileName = null; //文件旧名称
        String fileName = null; //文件新名称
        String prefix = null; //文件前缀
        //判断文件是否为空
        if(!multipartFile.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
            logger.info("uploadFile path ============== > "+path);
            oldFileName = multipartFile.getOriginalFilename();//原文件名
            logger.info("uploadFile oldFileName ============== > "+oldFileName);
            prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀
            logger.debug("uploadFile prefix============> " + prefix);
            int filesize = 600000;
            logger.debug("uploadFile size============> " + multipartFile.getSize());
            if(multipartFile.getSize() >  filesize){//上传大小不得超过 600k
                request.setAttribute("uploadFileError", " * 上传大小不得超过600k");
                return "main";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")
                    || prefix.equalsIgnoreCase("doc") || prefix.equalsIgnoreCase("docx")
                    || prefix.equalsIgnoreCase("ppt") || prefix.equalsIgnoreCase("pptx")
                    || prefix.equalsIgnoreCase("xlsx") || prefix.equalsIgnoreCase("html")
                    || prefix.equalsIgnoreCase("css") || prefix.equalsIgnoreCase("js")
                    || prefix.equalsIgnoreCase("java") || prefix.equalsIgnoreCase("py")
                    || prefix.equalsIgnoreCase("c") || prefix.equalsIgnoreCase("mp3")
                    || prefix.equalsIgnoreCase("gif") || prefix.equalsIgnoreCase("mp4")
                    || prefix.equalsIgnoreCase("rmvb") || prefix.equalsIgnoreCase("zip")
                    || prefix.equalsIgnoreCase("tar") || prefix.equalsIgnoreCase("txt")
                    || prefix.equalsIgnoreCase("md") || prefix.equalsIgnoreCase("pdf") ||
                    prefix.equalsIgnoreCase("rtf") || prefix.equalsIgnoreCase("xls")
                    || prefix.equalsIgnoreCase("caj")){
                // 对存入的图片还是文件进行判断
                fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000) + "_" + oldFileName;
                // 提示编辑区域
                session.setAttribute("upcontext", "*请双击下方文字区域编辑");

              /*  if(prefix.equalsIgnoreCase("doc") || prefix.equalsIgnoreCase("docx")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.doc";
                }else if (prefix.equalsIgnoreCase("ppt") || prefix.equalsIgnoreCase("pptx")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.ppt";
                }else if (prefix.equalsIgnoreCase("xlsx")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.xlsx";
                }else if (prefix.equalsIgnoreCase("js")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.js";
                }else if (prefix.equalsIgnoreCase("html")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.html";
                }else if (prefix.equalsIgnoreCase("css")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.css";
                }else if (prefix.equalsIgnoreCase("java")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.java";
                }else if (prefix.equalsIgnoreCase("py")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.py";
                }else if (prefix.equalsIgnoreCase("c")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.c";
                }else if (prefix.equalsIgnoreCase("gif")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.gif";
                }else if (prefix.equalsIgnoreCase("mp3")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.mp3";
                }else if (prefix.equalsIgnoreCase("mp4")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.mp4";
                }else if (prefix.equalsIgnoreCase("rmvb")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.rmvb";
                }else if (prefix.equalsIgnoreCase("zip")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.zip";
                }else if (prefix.equalsIgnoreCase("tar")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.tar";
                }else if (prefix.equalsIgnoreCase("txt")){
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.txt";
                }else {
                    fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";
                }*/
                logger.debug("new fileName======== " + multipartFile.getName());
                File targetFile = new File(path, fileName);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                //保存
                try {
                    multipartFile.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("uploadFileError", " * 上传失败！");
                    return "main";
                }
                upic = path+File.separator+fileName;
            }else{
                request.setAttribute("uploadFileError", " *上传文件格式不正确，目前支持:java,c,py,md,html,css,js,mp3,mp4,rmvb,zip,tar,gif,jpg,png,pneg,jpeg,txt,xls,doc,docx,ppt或xlsx等");
                return "main";
            }
        }else {
            return "main"; //如果没有上传文件返主页面
        }
        user.setUid(((User)session.getAttribute(Constants.USER_SESSION)).getUid());
        user.setUserCode(((User)session.getAttribute(Constants.USER_SESSION)).getUserCode());
        user.setUserPassword(((User)session.getAttribute(Constants.USER_PASS)).getUserPassword());
        user.setUpics(upic);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>路径为："+user.getUpics());
        //读取文本文件
        String pathname = user.getUpics();
        InputStreamReader reader = null;
        BufferedReader br = null;
        InputStream in = null;

         /*
       * 如果上传的是html文件
       * */
        if(prefix.equalsIgnoreCase("html")){
            logger.info("进入html文件上传>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            StringBuffer sb = new StringBuffer();
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(
                        pathname), "UTF-8"));
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            session.setAttribute("rword", sb.toString());
        }


        /*
        * 如果上传的是txt等文本内容
        * */
        if (prefix.equalsIgnoreCase("js") || prefix.equalsIgnoreCase("txt")
                || prefix.equalsIgnoreCase("xlsx")
                || prefix.equalsIgnoreCase("css") || prefix.equalsIgnoreCase("java")
                || prefix.equalsIgnoreCase("py") || prefix.equalsIgnoreCase("c")||
                prefix.equalsIgnoreCase("md") || prefix.equalsIgnoreCase("rtf")){
            try {
                file = new File(pathname); // 读取以上路径的input.txt文件
                reader = new InputStreamReader(
                        new FileInputStream(file), Charset.forName("utf-8")); // 建立一个输入流对象reader
                br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            /*in = new FileInputStream(file);

            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));*/
                // 读取一行，存储于字符串列表中
                List<String> lines=new ArrayList<String>(); //存放读取的文本信息
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    /*lines.add(line);*/
                    logger.info("line>>>>>>>>>>>>>>>>>>>>>>>>:" + lines.add(line));
                    logger.info("line>>>>>>>>>>>>>>>>>>>>>>>>:" + lines);
                }

                session.setAttribute(Constants.TEXT_TXT, lines);
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (br != null){
                        br.close();
                    }
                    if (reader != null){
                        reader.close();
                    }
                    if (in != null){
                        in.close();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        /*
        * 如果上传的是word文本
        * */
        //2003版本以前
        if (prefix.equalsIgnoreCase("doc")){
            logger.info("进入2003word文本读取>>>>>>>>>>>>>>>>>>>>>>>>>>");
            file = new File(pathname);
            FileInputStream fileInputStream = null;
            try {

                fileInputStream = new FileInputStream(file);
                WordExtractor wordExtractor = new WordExtractor(fileInputStream);
                String result = wordExtractor.getText();
                session.setAttribute("rword", result);
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        //2007版本及以后
        if (prefix.equalsIgnoreCase("docx")){
            logger.info("进入2007及以后word文本读取>>>>>>>>>>>>>>>>>>>>>>>>>>");
           /* File file = new File(pathname);*/
            try {
                OPCPackage opcPackage = POIXMLDocument.openPackage(pathname);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                String text = extractor.getText();
                session.setAttribute("rword", text);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
    *  上传的是PDF格式
    * */
        /*if(prefix.equalsIgnoreCase("pdf")){
            logger.info("进入pdf文件显示>>>>>>>>>>>>>>>>>>>>>>>>>");
            String result = null;
            FileInputStream is = null;
            PDDocument document = null;
            try {
                is = new FileInputStream(pathname);
                PDFParser parser = new PDFParser(is);
                parser.parse();
                document = parser.getPDDocument();
                PDFTextStripper stripper = new PDFTextStripper();
                result = stripper.getText(document);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (document != null) {
                    try {
                        document.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            session.setAttribute("rword", result);
        }*/
        if(prefix.equalsIgnoreCase("pdf")){
            logger.info("进入pdf上传显示>>>>>>>>>>>>>>>>>>>>>>>>>>");
            PdfReader readerPDF = null;
            StringBuffer buff = new StringBuffer();
            try {
                readerPDF = new PdfReader(pathname);
                PdfReaderContentParser parser = new PdfReaderContentParser(readerPDF);
                int num = readerPDF.getNumberOfPages();// 获得页数
                TextExtractionStrategy strategy;
                for (int i = 1; i <= num; i++) {
                    strategy = parser.processContent(i,
                            new SimpleTextExtractionStrategy());
                    buff.append(strategy.getResultantText());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //获取pdf文件文件中全部信息
            String txt = buff.toString();
            txt.replaceAll(" ", ""); //去除多余的空格
            txt.replaceAll("\\s", "");
            session.setAttribute("rword", txt);
            
        }

        /*
        * 上传的文件是RTF
        * */
       /* if (prefix.equalsIgnoreCase("rtf")){
            logger.info("进入显示rtf文件>>>>>>>>>>>>>>>>>>>>>>");
            String result = null;
            File file = new File(pathname);
            try {
                DefaultStyledDocument styledDoc = new DefaultStyledDocument();
                InputStream is = new FileInputStream(file);
                new RTFEditorKit().read(is, styledDoc, 0);
                result = new String(styledDoc.getText(0, styledDoc.getLength())
                        .getBytes("ISO8859_1"));
                // 提取文本，读取中文需要使用ISO8859_1编码，否则会出现乱码
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            session.setAttribute("rword",result);
        }*/

       /*
       * 文件是2003及以前版本,xls
       * */
       if (prefix.equalsIgnoreCase("xls")){
           StringBuffer buff = new StringBuffer();
           try {
               // 创建对Excel工作簿文件的引用
               HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(pathname));
               // 创建对工作表的引用。
               for (int numSheets = 0; numSheets < wb.getNumberOfSheets(); numSheets++) {
                   if (null != wb.getSheetAt(numSheets)) {
                       HSSFSheet aSheet = wb.getSheetAt(numSheets);// 获得一个sheet
                       for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
                           if (null != aSheet.getRow(rowNumOfSheet)) {
                               HSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行
                               for (int cellNumOfRow = 0; cellNumOfRow <= aRow
                                       .getLastCellNum(); cellNumOfRow++) {
                                   if (null != aRow.getCell(cellNumOfRow)) {
                                       HSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
                                       switch (aCell.getCellType()) {
                                           case HSSFCell.CELL_TYPE_FORMULA:
                                               break;
                                           case HSSFCell.CELL_TYPE_NUMERIC:
                                               buff.append(aCell.getNumericCellValue()).append('\t');
                                               break;
                                           case HSSFCell.CELL_TYPE_STRING:
                                               buff.append(aCell.getStringCellValue()).append('\t');
                                               break;
                                       }
                                   }
                               }
                               buff.append('\n');
                           }
                       }
                   }
               }
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
           session.setAttribute("rword", buff.toString());
       }

       /*
       * 文件上传的是2007及以后版本excel格式
       * */
       if (prefix.equalsIgnoreCase("xlsx")){

           StringBuffer buff = new StringBuffer();
           try {
               // 创建对Excel工作簿文件的引用
               XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(pathname));
               // 创建对工作表的引用。
               for (int numSheets = 0; numSheets < wb.getNumberOfSheets(); numSheets++) {
                   if (null != wb.getSheetAt(numSheets)) {
                       XSSFSheet aSheet = wb.getSheetAt(numSheets);// 获得一个sheet
                       for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
                           if (null != aSheet.getRow(rowNumOfSheet)) {
                               XSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行
                               for (int cellNumOfRow = 0; cellNumOfRow <= aRow
                                       .getLastCellNum(); cellNumOfRow++) {
                                   if (null != aRow.getCell(cellNumOfRow)) {
                                       XSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
                                       switch (aCell.getCellType()) {
                                           case HSSFCell.CELL_TYPE_FORMULA:
                                               break;
                                           case HSSFCell.CELL_TYPE_NUMERIC:
                                               buff.append(aCell.getNumericCellValue()).append('\t');
                                               break;
                                           case HSSFCell.CELL_TYPE_STRING:
                                               buff.append(aCell.getStringCellValue()).append('\t');
                                               break;
                                       }
                                   }
                               }
                               buff.append('\n');
                           }
                       }
                   }
               }
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
           
           session.setAttribute("tip", ",xlsx表如出现中文乱码(文本内容在乱码后边显示)，也可把格式改为xls上传即可"); //如出现中文乱码则提醒
           session.setAttribute("rword", buff.toString());
       }

        session.setAttribute("upath", "点击可下载：");
        session.setAttribute(Constants.UPIC_PATH,user.getUpics()); //储存文件的路径
        boolean flag = userService.updatePic(user);
	    //将用户添加到model
        model.addAttribute("user", user);
        session.setAttribute("filename", fileName); //保存源文件名

        //储存文件数目统计
        String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles" + File.separator);
        file = new File(path);
        int num = getFileNum(file);
        session.setAttribute("filecount", "系统文件总个数：" + num);

        logger.info("+++++++++++++++++user**************************:" + fileName);
        if(flag == true){
            return "redirect:/sys/main.html";
        }
        return "main";
    }

    /*
    * 文件下载
    * */

   /* @RequestMapping(value="download.html")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam(value = "filename", required = false) String filename)throws Exception {
        //下载文件路径
        String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片或文件
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 利用二进制流数据，常见的文件下载
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }*/
   /*
   * 文件下载
   * */
    @RequestMapping(value="download.html")
    public String download(HttpServletRequest request,HttpServletResponse response,
                                           @RequestParam(value = "filename", required = false) String filename)throws Exception {
        response.setCharacterEncoding("utf-8");
        //下载文件路径
        String filePath = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
        String tempFileName = new String(filename.getBytes("ISO8859-1"), "UTF-8");
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("application/x-msdownload");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName="+new String(tempFileName.getBytes("UTF-8"),"ISO8859-1"));  //这里的fileName要是ISO8859-1编码
        ServletOutputStream out = null;
        FileInputStream is = null ;
        //通过文件路径获得File对象
        File file = new File(filePath + File.separator + filename);
        try {
            is= new FileInputStream(file);
            //3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while ((b=is.read(buffer,0,buffer.length))!= -1){
                //4.写到输出流(out)中
                out.write(buffer,0,b);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(is!=null){
                    is.close();
                }
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    /*
    * 文件删除
    * */

  /*  @RequestMapping(value = "deletefile.html")
    public String deleteFile(HttpServletRequest request, HttpSession session){
        //统计文件夹中文件的个数
        String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles" + File.separator);
        File file = new File(path);
        int num = getFileNum(file); //调用文件数目统计
        logger.info("文件夹的路径为>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + path);
        logger.info("num文件个数为：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + num);
        //避免文件太多堵塞服务器，文件个数每次超过1000个就进行删除
        if (num > 400) {
            // 调用删除文件的方法
            String[] children = file.list();
            if (children == null) {
                logger.info( "目录不存在或它不是一个目录");
            }
            else {
                for (int i=0; i< children.length; i++) {
                    String filename = children[i];
                    logger.info("filename>>>>>>>>>>>>" + filename);
                    file.delete();
                    logger.info("删除成功！>>>>>>>>>>>>");
                }
            }
        }

        return "main";
    }*/
    /*
    * 跳转至文件页面
    * */
    @RequestMapping(value = "skipfile.html")
    public String skip(User user,HttpSession session){
       user.setUpics(((User)session.getAttribute(Constants.USER_SESSION)).getUpics());
       session.setAttribute(Constants.UPIC_PATH, user.getUpics());
       logger.info("跳转的路径为>>>>>>>>>>>>***********" + user.getUpics());
        /*return "fileload";*/
        return  "uploadForm";
    }

    /*
    * 清空上传文件
    * */

    @RequestMapping(value = "deleteupics.html")
    public String deletePic(HttpSession session){
        logger.info("进入清空文件>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Integer uid1 = ((User)session.getAttribute(Constants.USER_SESSION)).getUid();
        userService.deleteUserPics(uid1);
        session.setAttribute("clear","删除成功!");
        return "main";
    }

    /*
    * 查重功能的实现
    * */

    @RequestMapping(value = "rep.html")
    public String repTxt(HttpSession session, HttpServletRequest request,
                         @RequestParam(value = "filename", required = false) String filename){

        logger.info("进入查重系统>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        String filePath = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");

        // 利用SimHash实现对比查重
        String s = "this is time to go";
        SimHash hash1 = new SimHash(s, 128);
        s = "this is time all";
        SimHash hash2 = new SimHash(s, 128);
        s = "this is time to ga";
        SimHash hash3 = new SimHash(s, 128);
        session.setAttribute("reptxt1", hash1.hammingDistance(hash2) + "/测试...");
        session.setAttribute("reptxt2", hash1.hammingDistance(hash3));
        return "main";
    }

    /*
    * 神级pdf操作
    * */
    @RequestMapping(value = "pdfdo.do")
    public String pdfDo(HttpServletRequest request, HttpServletResponse response
            , HttpSession session, @RequestParam(value = "filename", required = false) String filename){
        response.setCharacterEncoding("utf-8");
        //下载文件路径
        String filePath = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles" + File.separator + filename);
        logger.info("filename>>>>>>>>>>>>>>>>>>>>>>>>>>>:" + filename);
        logger.info("filepath>>>>>>>>>>>>>>>>>>>>>>>>>>>:" + filePath);
        try {
            logger.info("file+name*************************" + filePath);
            session.setAttribute("pdfpath", filePath);
            session.setAttribute(Constants.PDF_FILENAME, filename);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "showpdf";
    }

 /*   @RequestMapping(value = "showpdf.html")
    public ModelAndView showpdf(){
        return
    }
*/

    /*
       * 跳转到换选导师页面
       * */
    @RequestMapping(value = "exchange.html")
    public String exChange(HttpSession session){
        /*logger.info(">>>>>>>>>>>>>>>>>>>>>>>进入导师信息查询页面！");
        List<Ex> exList = userService.showExInfo();
        session.setAttribute("exList", exList);*/

        return "change";
    }

    /*
    * 学生信息增加显示
    *
    * */
    @RequestMapping(value = "exadd.html", method = RequestMethod.POST)
    public String exAdd(Ex ex, HttpSession session, @RequestParam String exname){
        boolean flag = false;
        if (userService.exExist(exname) != null && !userService.exExist(exname).equals("")){
            session.setAttribute("exExist","该昵称可以使用！");
        }else {
            flag = userService.addExs(ex);
            if (flag == true){
                if (ex.getExname() != null && ex.getExteacher() !=null &&
                        ex.getExphone() != null && ex.getExname() != "" &&
                        ex.getExteacher() != "" && ex.getExphone() != ""){
                        session.setAttribute("extip", "提交成功！");
                }

            }else {
                session.setAttribute("extip", "提交失败！昵称已经存在并且不能为空！");
            }
        }
        return "change";
    }

    /*
    * 导师信息保存
    * */
    @RequestMapping(value = "exsave.html")
    public String exSave(HttpSession session){
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>进入导师信息查询页面！");
        List<Ex> exList = userService.showExInfo();
        session.setAttribute("exList", exList);
        session.setAttribute("count", userService.exNums());
        return "change";
    }

    /*
    * 导师信息修改
    * */
    @RequestMapping(value = "updateex.html",method = RequestMethod.GET)
    public String updateEx(HttpSession session,@RequestParam("exid") String exid, @RequestParam("exname") String exname,
                           @RequestParam("exteacher") String exteacher,
                           @RequestParam("exphone") String exphone){
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>进入导师信息修改");
        Ex ex = new Ex();
        if (exid != null && exid != "" && exname != null && exname != ""
                && exteacher != null && exteacher != "" &&
                exphone != null && exphone != ""){
            Integer intId = Integer.parseInt(exid);
            ex.setExid(intId);
            ex.setExphone(exphone);
            ex.setExteacher(exteacher);
            ex.setExname(exname);
            Integer exUser = userService.updateExs(ex);
            if (exUser > 0){
                session.setAttribute("updateUser","*修改成功");
            }
        } else {
            session.setAttribute("updateUser","*修改失败，输入不能留空");
        }
        return "change";
    }

    /*
    * 交换系统返回首页
    * */
    @RequestMapping(value = "backmain.html")
    public String back(){
        return "main";
    }

   /* *//*
    * 储存修改信息
    * *//*
    @RequestMapping(value = "saveExUpdate.html")
    public String saveExUpdate(){

        return "change";
    }*/

    /*
	* ajax异步判断导师系统昵称用户是否重名
	* */
    @RequestMapping(value = "exnameExist.html")
    @ResponseBody //异步处理结果直接写入HTTP ResponseBody中
    public Object userCodeIsExist(@RequestParam String exname){
        logger.info("///////////*****************////////////进入昵称异步判断");
        String cjson = null;
        logger.debug("exnameIsExit exname===================== "+exname);
        if (StringUtils.isNullOrEmpty(exname)){
			/*resultMap.put("userCode", "exist");*/ //如果用户已经存在
            logger.info("//////************************进入null:");
            return "nullcode";
        }else {
            try {
                Ex exUser = userService.exExist(exname);
                logger.info("exname+++++++++++++++++++++++++" + exUser);
                if (null != exUser){
                    cjson = "exist"; //用户不为空则已存在
                }else {
                    cjson = "noexist"; //用户为空，则可以注册
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("//////************************cjson:" + cjson);
        return cjson;
    }

}
