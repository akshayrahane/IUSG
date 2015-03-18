// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   jspChart.java

package javaside.Rbl;

import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.Vector;
import javaside.Rbl.util.GifEncoder;
import javaside.Rbl.util.ImageEncoder;
import javaside.Rbl.util.PngEncoder;

// Referenced classes of package javaside.Rbl:
//            ichart

public class jspChart
{
    class rd
    {

        public boolean get(rd rd1, String s, char c, int i)
        {
            boolean flag = false;
            int l = 0;
            int i1 = 0;
            Double double1 = new Double(0.0D);
            iN = i;
            iD = new double[iN];
            for(int j = 0; j < iN;)
                iD[j++] = jspChart.iNan;

            i = 0;
            if(s == null)
                return false;
            do
            {
                int k = s.indexOf(c, i);
                String s1;
                if(k > 0){
                    s1 = new String(s.substring(i, k).trim());
                   }
                else
                    s1 = new String(s.substring(i).trim());
                boolean flag1;
                try
                {
                    double1 = Double.valueOf(s1);
                         if(i1 < 1)
                        flag1 = false;
                    else
                        flag1 = true;
                }
                catch(NumberFormatException numberformatexception)
                {
                    flag1 = false;
                }
                if(s1.length() == 0 && i1 > 0)
                {
                    flag1 = true;
                    double1 = new Double(jspChart.iNan);
                }
                if(k > -1 || s1.length() > 0)
                    if(flag1)
                    {
                        if(l < iN)
                            rd1.iD[l++] = double1.doubleValue();
                    } else
                    if(s1.startsWith("frm="))
                        rd1.sDec = s1.substring(4);
                    else
                    if(s1.startsWith("ilg="))
                        rd1.iLg = Integer.parseInt(s1.substring(4));
                    else
                    if(s1.startsWith("unit="))
                    {
                        rd1.sU = s1.substring(5);
                    } else
                    {
                        if(i1 == 0)
                        { rd1.sIt = new String(s1);}
                        if(i1 == 1)
                            rd1.sUrl = new String(s1);
                        if(i1 == 2)
                            rd1.sFont = new String(s1);
                        if(i1 == 3)
                            rd1.sBg = new String(s1);
                        i1++;
                    }
                if(k == -1)
                    return l + i1 > 0;
                i = k + 1;
            } while(true);
        }

        public int iN;
        public String sIt;
        public String sUrl;
        public String sFont;
        public String sBg;
        public String sU;
        public int iLg;
        public String sDec;
        public double iD[];

        rd()
        {
            iN = 1;
            sIt = "";
            sUrl = "";
            sFont = "Arial";
            sBg = "";
            sU = "";
            iLg = 1;
            sDec = "";
        }
    }


    public jspChart()
    {
        frame = null;
        g = null;
        image = null;
        cSep = ';';
        cv1 = null;
        iMax = 1;
        iCol = 1;
        sFile = null;
        zMin = 0;
        zMax = 0;
        iPres = 0;
        iPres2 = 1;
        iW = 0;
        iH = 0;
    }

    public void init(int i, int j)
    {
        init(i, j, null);
    }

    public void init(int i, int j, String s)
    {
        iW = i;
        iH = j;
        Frame frame1 = null;
        frame1 = new Frame();
        frame1.addNotify();
        cv1 = new ichart(frame1);
        if(s != null)
            initProperties(s);
        try
        {
            image = frame1.createImage(i, j);
            g = image.getGraphics();
        }
        catch(Exception exception)
        {
            System.out.println("--> Erreur 1 : ".concat(String.valueOf(String.valueOf(exception))));
        }
    }

    public void initProperties(String s)
    {
        if(s == null)
            return;
        Properties properties = new Properties();
        try
        {
            FileInputStream fileinputstream = new FileInputStream(s);
            properties.load(fileinputstream);
            fileinputstream.close();
        }
        catch(Exception exception)
        {
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("--> e1: ")).append(exception).append(" / ").append(s))));
        }
        setSep(properties.getProperty("SEPARATOR", ";").charAt(0));
        setFont(new Font(properties.getProperty("FONTFACE_A", "Dialog"), Integer.parseInt(properties.getProperty("FONTSTYLE_A", "0")), Integer.parseInt(properties.getProperty("FONTSIZE_A", "8"))), 'A');
        setFont(new Font(properties.getProperty("FONTFACE_T", "Dialog"), Integer.parseInt(properties.getProperty("FONTSTYLE_T", "1")), Integer.parseInt(properties.getProperty("FONTSIZE_T", "12"))), 'T');
        setFont(new Font(properties.getProperty("FONTFACE_L", "Dialog"), Integer.parseInt(properties.getProperty("FONTSTYLE_L", "0")), Integer.parseInt(properties.getProperty("FONTSIZE_L", "9"))), 'L');
        sFile = properties.getProperty("FILE", sFile);
        zMin = Integer.parseInt(properties.getProperty("MIN", "0"));
        zMax = Integer.parseInt(properties.getProperty("MAX", "0"));
        iCol = Integer.parseInt(properties.getProperty("COL", "1"));
        iPres = Integer.parseInt(properties.getProperty("PRESENTATION", "0"));
        iPres2 = Integer.parseInt(properties.getProperty("XTDPRESS", "1"));
        setRotate(properties.getProperty("ROTATE", "1").charAt(0) == '1');
        setCol(iCol);
        readFile(sFile);
        setMinMax(zMin, zMax);
        setPress(iPres);
    }

    public void setSep(char c)
    {
        cSep = c;
    }

    public void setSep(String s)
    {
        if(s != null && s.length() > 0)
            cSep = s.charAt(0);
    }

    public void setRotate(boolean flag)
    {
        cv1.setRotate(flag);
    }

    public void setOrigine(int i, int j)
    {
        cv1.setOrigine(i, j);
    }

    public void clear()
    {
        if(frame != null)
            frame.removeNotify();
        if(g != null)
            g.dispose();
    }

    public void readFile(String s)
    {
        dFile.removeAllElements();
        BufferedReader bufferedreader = null;
        int i = 0;
        Object obj = null;
        String s1 = "";
        try
        {
            bufferedreader = new BufferedReader(new FileReader(s));
        }
        catch(Exception exception)
        {
            setTitle("erreur".concat(String.valueOf(String.valueOf(exception))));
            return;
        }
        byte byte0 = 32;
        do
        {
            String s2;
            try
            {
                s2 = bufferedreader.readLine();
            }
            catch(Exception exception1)
            {
                break;
            }
            char c;
            if(s2 != null)
                c = s2.charAt(0);
            else
                c = ' ';
            if(c == ';' || c == '/')
                continue;
            rd rd1 = new rd();
            if(!rd1.get(rd1, s2, cSep, iCol))
                break;
            if(rd1.sIt.equals("col"))
                cv1.addCol(i++, (int)rd1.iD[0], rd1.sUrl);
            else
            if(rd1.sIt.equals("title"))
                setTitle(rd1.sUrl);
            else
            if(rd1.sIt.equals("label"))
                setLegend(rd1.sUrl, rd1.sFont);
            else
                addRow(rd1.iD, rd1.sIt);
        } while(true);
    }

    public boolean build(boolean flag)
    {
        cv1.paint(g, iW, iH, flag);
        return true;
    }

    public boolean build(int i)
    {
        cv1.paint(g, iW, iH, i);
        return true;
    }

    public boolean encode(OutputStream outputstream, int i)
    {
        try
        {
            if(i == 0)
            {
                GifEncoder gifencoder = new GifEncoder(image, outputstream);
                gifencoder.transparentRgbForced = (new Color(-1)).getRGB() & 0xffffff;
                gifencoder.encode();
            } else
            {
                PngEncoder pngencoder = new PngEncoder(image);
                pngencoder.setCompressionLevel(9);
                byte abyte0[] = pngencoder.pngEncode();
                if(abyte0 == null)
                    System.out.println("Null image");
                else
                    outputstream.write(abyte0);
                outputstream.flush();
            }
        }
        catch(Exception exception)
        {
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("--> Erreur 3 : ")).append(exception).append("image=").append(image).append(" out=").append(outputstream))));
            boolean flag = false;
            return flag;
        }
        return true;
    }

    public void saveAs(String s)
    {
        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(s);
            if(s.toLowerCase().indexOf(".png") > 0)
                fileoutputstream.write(getImage(1));
            else
                fileoutputstream.write(getImage(0));
            fileoutputstream.flush();
            fileoutputstream.close();
        }
        catch(Exception exception)
        {
            System.out.println("--> Erreur 4 : ".concat(String.valueOf(String.valueOf(exception))));
        }
    }

    public byte[] getImage(int i)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, 240, 18);
        g.setColor(Color.black);
        g.setFont(new Font("Dialog", 1, 10));
        //g.drawString("www.javaside.com : jspChart V 1.65", 3, 17);
        byte abyte0[] = null;
        try
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(0x186a0);
            if(!encode(bytearrayoutputstream, i))
                System.out.println("--> Erreur encode");
            abyte0 = bytearrayoutputstream.toByteArray();
            bytearrayoutputstream.flush();
            bytearrayoutputstream.close();
        }
        catch(Exception exception)
        {
            System.out.println("--> Erreur 5 : ".concat(String.valueOf(String.valueOf(exception))));
        }
        return abyte0;
    }

    public void setBgImg(String s, int i)
    {
    }

    public void setUnit(String s)
    {
        cv1.setUnit(s);
    }

    public void setDec(int i)
    {
        cv1.setDec(i);
    }

    public void setDec(String s)
    {
        cv1.setDec(s);
    }

    public void setDecS(String s)
    {
        cv1.setDec(s);
    }

    public void setFont(String s, int i)
    {
        cv1.setFont(s, 0, i);
    }

    public void setFontA(String s, int i, int j)
    {
        setFont(new Font(s, i, j), 'A');
    }

    public void setFontTitre(String s, int i, int j)
    {
        setFont(new Font(s, i, j), 'T');
    }

    public void setFontLegend(String s, int i, int j)
    {
        setFont(new Font(s, i, j), 'L');
    }

    public void setFont(Font font, char c)
    {
        cv1.setFont(font, c);
    }

    public void setBkColor(int i)
    {
        cv1.setBkColor(i);
    }

    public void setPress(int i)
    {
        cv1.setPress(i);
    }

    public void setXPress(boolean flag, boolean flag1, boolean flag2)
    {
        cv1.setXPress(flag, flag1, flag2);
    }

    public void clearData()
    {
        cv1.clearData();
        dFile.removeAllElements();
        iMax = 0;
    }

    public void setCol(int i)
    {
        clearData();
        cv1.setCol(i);
        iCol = i;
    }

    public void setTitle(String s)
    {
        cv1.setTitle(s);
    }

    public void addCol(int i, int j, String s)
    {
        cv1.addCol(i, j, s);
    }

    public void addRow(String s)
    {
        rd rd1 = new rd();
        if(rd1.get(rd1, s, cSep, iCol))
            cv1.addRow(rd1.iD, rd1.sIt);
    }

    public void addRow(double ad[], String s)
    {
        cv1.addRow(ad, s);
    }

    public void setVal(int i)
    {
        if(i > 0)
            cv1.setVal(i);
        else
            cv1.setVal(-1);
    }

    public void setMinMax(double d, double d1)
    {
        if(d != d1)
            cv1.setMinMax(d, d1);
    }

    public void setLegend(String s, String s1)
    {
        cv1.setLegend(s, s1);
    }

    static double iNan = -0.00012540000000000001D;
    private static Vector dFile = new Vector(5, 5);
    Frame frame;
    Graphics g;
    Image image;
    char cSep;
    ichart cv1;
    private int iMax;
    private int iCol;
    String sFile;
    int zMin;
    int zMax;
    int iPres;
    int iPres2;
    int iW;
    int iH;

}
