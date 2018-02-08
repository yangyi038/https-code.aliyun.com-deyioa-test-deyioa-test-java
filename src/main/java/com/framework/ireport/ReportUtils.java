package com.framework.ireport;

import java.io.IOException;   
import java.io.ObjectOutputStream;
import java.io.OutputStream;   
import java.io.PrintWriter;   
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;   
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;   
import java.util.Map;   
  

import javax.servlet.ServletException;   
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
import javax.servlet.http.HttpSession; 
import javax.xml.transform.stream.StreamResult;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
  

import net.sf.jasperreports.engine.JRAbstractExporter;   
import net.sf.jasperreports.engine.JRDataSource;   
import net.sf.jasperreports.engine.JRException;   
import net.sf.jasperreports.engine.JRExporterParameter;   
import net.sf.jasperreports.engine.JasperCompileManager;   
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;   
import net.sf.jasperreports.engine.JasperPrint;   
import net.sf.jasperreports.engine.JasperReport;   
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;   
import net.sf.jasperreports.engine.export.JExcelApiExporter;   
import net.sf.jasperreports.engine.export.JRHtmlExporter;   
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;   
import net.sf.jasperreports.engine.export.JRPdfExporter;   
import net.sf.jasperreports.engine.export.JRRtfExporter;   
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;   
import net.sf.jasperreports.engine.util.JRLoader;   
import net.sf.jasperreports.j2ee.servlets.ImageServlet;   
  
/**  
 * 使用jasperReport做报表时的工具支持类.有两个用途,生成jasperPrint对象,和设置导出时的session  
 */  
public class ReportUtils {   
    private HttpServletRequest request;   
    private HttpServletResponse response;   
    private HttpSession session;   
    private JdbcTemplate yzJdbcTemplate;
    /**  
     * 在其它web环境下构造此工具类对象  
     *   
     * @param request  
     *            request请求对象  
     */  
    public ReportUtils(HttpServletRequest request) {   
        this.request = request;   
        this.session = request.getSession();   
    }   
    public ReportUtils() {
    }  
    public ReportUtils(HttpServletResponse response) {   
        this.response = response;   
    }   
  
    public ReportUtils(HttpServletRequest request, HttpServletResponse response) {   
        this(request);   
        this.response = response;   
    }   
    public ReportUtils(HttpServletRequest request, HttpServletResponse response,JdbcTemplate yzJdbcTemplate) {   
        this(request);   
        this.response = response; 
        this.yzJdbcTemplate = yzJdbcTemplate;  
    } 
    /**  
     * 获得JasperPrint对象;自定义填充报表时的parameter和dataSource. 参数说明和动态表头的用法参考上一方法  
     *   
     * @param filePath  
     * @param parameter  
     * @param dataSource  
     * @param sizeGroup  
     * @return  
     */  
    public JasperPrint getJasperPrint(String filePath, Map parameter,   
            JRDataSource dataSource,String type) throws JRException {   
        JasperReport jasperReport = null;   
        try {   
            jasperReport = (JasperReport)JRLoader.loadObjectFromFile(filePath);
            if(type.equals("jdbc")){
            	Connection conn=null;
        	try{
        		conn=DataSourceUtils.getConnection(yzJdbcTemplate.getDataSource());
        		JasperPrint jasperPrint= JasperFillManager.fillReport(filePath, parameter, conn);
        		return jasperPrint;
        	} catch (JRException e) {   
                e.printStackTrace();   
            } finally {
            	try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
            	
            }else{
            	return JasperFillManager.fillReport(jasperReport, parameter,   
                        dataSource);  
            	
            
            }
        } catch (JRException e) {   
            e.printStackTrace();   
        }   
        return null;   
    }   
  
    /**  
     * 通过传入List类型数据源获取JasperPrint实例  
     *   
     * @param filePath  
     *            jasper路径  
     * @param parameter  
     * @param list  
     * @return  
     * @throws JRException  
     */  
    public JasperPrint getPrintWithBeanList(String filePath, Map parameter,   
            List list,String type) throws JRException {   
        JRDataSource dataSource = new JRBeanCollectionDataSource(list); 

        return getJasperPrint(filePath, parameter, dataSource,type);   
    }   
  
    /**  
     * 传入类型，获取输出器  
     *   
     * @param docType  
     * @return  
     */  
    public JRAbstractExporter getJRExporter(DocType docType) {   
        JRAbstractExporter exporter = null;   
        switch (docType) {   
        case PDF:   
            exporter = new JRPdfExporter();   
            break;   
        case HTML:   
            exporter = new JRXhtmlExporter();   
            break;   
        case XLS:   
            exporter = new JExcelApiExporter();   
            break;   
        case XML:   
            exporter = new JRXmlExporter();   
            break;   
        case RTF:   
            exporter = new JRRtfExporter();   
            break;   
        }   
        return exporter;   
    }   
  
    public void setAttrToPage(JasperPrint jasperPrint, String report_fileName,   
            String report_type) {   
        session.setAttribute("REPORT_JASPERPRINT", jasperPrint);   
        session.setAttribute("REPORT_FILENAME", report_fileName);   
        session.setAttribute("REPORT_TYPE", report_type);   
    }   
  
    /**  
     * 定义了报表输出类型，固定了可输出类型  
     *   
     * @author Administrator  
     *   
     */  
    public static enum DocType {   
        PDF, HTML, XLS, XML, RTF   
    }   
  
    /**  
     * 编译报表模板文件jaxml，生成jasper二进制文件  
     *   
     * @param jaxmlPath  
     * @param jasperPath  
     * @throws JRException  
     */  
    public void complieJaxml(String jaxmlPath, String jasperPath)   
            throws JRException {   
        JasperCompileManager.compileReportToFile(jaxmlPath, jasperPath);   
    }   
  
    /**  
     * 输出PDF 使用此方法，必须预先注入response  
     *   
     * @param jasperPath  
     * @param params  
     * @param sourceList  
     * @param fileName  
     * @throws JRException  
     * @throws IOException  
     * @throws ServletException  
     */  
    public void servletExportPDF(String jasperPath, Map params,   
            List sourceList, String fileName,String type) throws JRException, IOException,   
            ServletException {   
        servletExportDocument(DocType.PDF, jasperPath, params, sourceList,   
                fileName,type);   
    }   
    public void servletExportDoc(String jasperPath, Map params,   
            List sourceList, String fileName,String type) throws JRException, IOException,   
            ServletException {   
        servletExportDocument(DocType.RTF, jasperPath, params, sourceList,   
                fileName,type);   
    }
  
    /**  
     * 输出html静态页面，必须注入request和response  
     *   
     * @param jasperPath  
     * @param params  
     * @param sourceList  
     * @param imageUrl  
     *            报表文件使用的图片路径，比如 ../servlets/image?image=  
     * @throws JRException  
     * @throws IOException  
     * @throws ServletException  
     */  
    public void servletExportHTML(String jasperPath, Map params,   
            List sourceList, String imageUrl,String type) throws JRException, IOException,   
            ServletException {   
        response.setContentType("text/html");   
        response.setCharacterEncoding("UTF-8");   
        JRAbstractExporter exporter = getJRExporter(DocType.HTML);   
  
        JasperPrint jasperPrint = getPrintWithBeanList(jasperPath, params,   
                sourceList,type);   
  
        session.setAttribute(   
                ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,   
                jasperPrint);   
        
        PrintWriter out = response.getWriter();   
        
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
        exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
        if("".equals(imageUrl)){
        	exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        }else{
        	exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageUrl);
        }
        exporter.exportReport();   
    }   
  
    /**  
     * 输出Excel报表文件  
     *   
     * @param jasperPath  
     * @param params  
     * @param sourceList  
     * @param fileName  
     * @throws JRException  
     * @throws IOException  
     * @throws ServletException  
     */  
    public void servletExportExcel(String jasperPath, Map params,   
            List sourceList, String fileName,String type) throws JRException, IOException,   
            ServletException {   
        servletExportDocument(DocType.XLS, jasperPath, params, sourceList,   
                fileName,type);   
        // 要想获得更好的视觉效果，可以添加以下代码   
        // // exporter.setParameter(   
        // // JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,   
        // // Boolean.TRUE); // 删除记录最下面的空行   
        // //   
        // exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,   
        // // Boolean.FALSE);// 删除多余的ColumnHeader   
        // //   
        // exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND   
        // ,   
        // // Boolean.FALSE);// 显示边框   
    }   
    public void servletExportDocument(DocType docType, String jasperPath,   
            Map params, List sourceList, String fileName,String type) throws JRException,   
            IOException, ServletException{
    	servletExportDocument(docType, jasperPath, params, sourceList, fileName, 0, 0,type);
    }
    /**  
     * 生成不同格式报表文档  
     *   
     * @param docType  
     *            文档类型  
     * @param jasperPath  
     * @param params  
     * @param sourceList  
     * @param fileName  
     * @throws JRException  
     * @throws IOException  
     * @throws ServletException  
     */  
    public void servletExportDocument(DocType docType, String jasperPath,   
            Map params, List sourceList, String fileName,int x,int y,String type) throws JRException,   
            IOException, ServletException {   
  
        if (docType == DocType.HTML) {   
            servletExportHTML(jasperPath, params, sourceList, fileName,type);   
            return;   
        }   
  
        JRAbstractExporter exporter = getJRExporter(docType);   
        // 获取后缀   
        String ext = docType.toString().toLowerCase();   
  
        if (!fileName.toLowerCase().endsWith(ext)) {   
            fileName += "." + ext;   
        }   
        // 判断资源类型   
        String contentType = "application/";   
        if (ext.equals("xls")) {   
            ext = "excel";   
        } else if (ext.equals("xml")) {   
            contentType = "text/";   
        }   
        contentType += ext;   
  
        response.setContentType(contentType);   
        response.setHeader("Content-Disposition", "attachment; filename=\""  
                + URLEncoder.encode(fileName, "UTF-8") + "\"");   
  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,   
                getPrintWithBeanList(jasperPath, params, sourceList,type));   
  
         OutputStream ouputStream = response.getOutputStream(); 
        
        exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(x));
        exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(y));
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);   
        try {   
            exporter.exportReport();   
        } catch (JRException e) {   
            throw new ServletException(e);   
        } finally {   
            if (ouputStream != null) {   
                try {   
                    ouputStream.close();   
                } catch (IOException ex) {   
                }   
            }   
        } 
    }
    public void inlineExportDocument(DocType docType, String jasperPath,   
            Map params, List sourceList, String fileName,String type) throws JRException,   
            IOException, ServletException{
    	inlineExportDocument(docType, jasperPath, params, sourceList, fileName, 0, 0,type);
    }
    /**  
     * 生成不同格式报表文档 ，<font color='red'>文档直接在窗口中打开</font>
     *   
     * @param docType  
     *            文档类型  
     * @param jasperPath  
     * @param params  
     * @param sourceList  
     * @param fileName  
     * @throws JRException  
     * @throws IOException  
     * @throws ServletException  
     */  
    public void inlineExportDocument(DocType docType, String jasperPath,   
            Map params, List sourceList, String fileName,int x,int y,String type) throws JRException,   
            IOException, ServletException {   
  
        if (docType == DocType.HTML) {   
            servletExportHTML(jasperPath, params, sourceList, fileName,type);   
            return;   
        }   
  
        JRAbstractExporter exporter = getJRExporter(docType);   
        // 获取后缀   
        String ext = docType.toString().toLowerCase();   
  
        if (!fileName.toLowerCase().endsWith(ext)) {   
            fileName += "." + ext;   
        }   
        // 判断资源类型   
        String contentType = "application/";   
        if (ext.equals("xls")) {   
            ext = "excel";   
        } else if (ext.equals("xml")) {   
            contentType = "text/";   
        }   
        contentType += ext;   
  
        response.setContentType(contentType);   
        response.setHeader("Content-Disposition", "inline; filename=\""  
                + URLEncoder.encode(fileName, "UTF-8") + "\"");   
  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,   
                getPrintWithBeanList(jasperPath, params, sourceList,type));   
  
        OutputStream ouputStream = response.getOutputStream(); 
        
        exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(x));
        exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(y));
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);   
        try {   
            exporter.exportReport();   

        } catch (JRException e) {   
            throw new ServletException(e);   
        } finally {   
            if (ouputStream != null) {   
                try {   
                    ouputStream.close();   
                } catch (IOException ex) {   
                }   
            }   
        }   
    }
    
    public void inlineExportDocument(DocType docType, List reportBeanList,
    		String fileName,int x,int y,String type) throws JRException,   
            IOException, ServletException {   
  
        if (docType == DocType.HTML) {      
            return;   
        }   
  
        JRAbstractExporter exporter = getJRExporter(docType);   
        
        ArrayList jasperPrintList = new ArrayList();
        if(reportBeanList!=null&&reportBeanList.size()>0){
	        for(int i=0;i<reportBeanList.size();i++){
	        	ReportBean rb = (ReportBean) reportBeanList.get(i);
	        	JasperPrint jp = getPrintWithBeanList(rb.getJasperPath(), rb.getParams(), rb.getSourceList(),type);
	        	jasperPrintList.add(jp);
	        }
    	}

        
        // 获取后缀   
        String ext = docType.toString().toLowerCase();   
  
        if (!fileName.toLowerCase().endsWith(ext)) {   
            fileName += "." + ext;   
        }   
        // 判断资源类型   
        String contentType = "application/";   
        if (ext.equals("xls")) {   
            ext = "excel";   
        } else if (ext.equals("xml")) {   
            contentType = "text/";   
        }   
        contentType += ext;   
  
        response.setContentType(contentType);   
        response.setHeader("Content-Disposition", "inline; filename=\""  
                + URLEncoder.encode(fileName, "UTF-8") + "\"");   
  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,   
        		jasperPrintList);   
  
        OutputStream ouputStream = response.getOutputStream(); 
        
        exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(x));
        exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(y));
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);   
        try {   
            exporter.exportReport();   
        } catch (JRException e) {   
            throw new ServletException(e);   
        } finally {   
            if (ouputStream != null) {   
                try {   
                    ouputStream.close();   
                } catch (IOException ex) {   
                }   
            }   
        }   
    }
    
    public void getJasperPrintList(DocType docType, List reportBeanList,String type) throws JRException,   
            IOException, ServletException {
        ArrayList jasperPrintList = new ArrayList();
        if(reportBeanList!=null&&reportBeanList.size()>0){
	        for(int i=0;i<reportBeanList.size();i++){
	        	ReportBean rb = (ReportBean) reportBeanList.get(i);
	        	JasperPrint jp = getPrintWithBeanList(rb.getJasperPath(), rb.getParams(), rb.getSourceList(),type);
	        	jasperPrintList.add(jp);
	        }
    	} 
        session.setAttribute("docType", docType);
        session.setAttribute("JasperPrintList", jasperPrintList);
    }
    public void saveExportPdf(List reportBeanList,String jasperpath,Map params,
    		String fileName,String path) throws JRException,   
            IOException, ServletException {   
    	 //   JRDataSource dataSource = this.createDataSource();
    	  JRDataSource dataSource = new JRBeanCollectionDataSource(reportBeanList);;
    	 //   Map<String, Object> parameters = new HashMap<String, Object>();
    	 //   List dataList =new ArrayList();
    	 //   parameters.put("dataList", dataList);
    	  JasperReport report = (JasperReport)JRLoader.loadObjectFromFile(jasperpath);
    	  
    	  JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
    	  JasperExportManager.exportReportToPdfFile(jasperPrint,path+fileName);
    	 // JasperRunManager.runReportToHtmlFile(root_path+"report/123.pdf",null,null);
    	//如果创建报表成功，则转向该报表，其实可以把报表套在框架内，这样实现比较有意义的报表格式。
    	//response.sendRedirect("report2.html");
  
    }
}  
