// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ichart.java

package javaside.Rbl;

import java.awt.*;
import java.awt.image.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Vector;
import javaside.Rbl.util.RotateFilter;

public class ichart {
	class rq {

		public String sIt;
		public String sUrl;
		public double iD[];

		public rq(int i) {
			sIt = "";
			sUrl = "";
			iD = new double[i];
		}
	}

	public ichart(Frame f) {
		iNan = -0.00012540000000000001D;
		Rotate = false;
		v = new Vector(10, 5);
		df1 = new DecimalFormat("#0");
		cBack = 0;
		X = 40;
		Y = 40;
		iY = 1.7976931348623157E+308D;
		bgWidth = -1;
		bgHeight = -1;
		fPas = 1.0D;
		fPas2 = 1.0D;
		iCol = 1;
		sMax = false;
		iUnit = 1.0D;
		sUnit = "";
		iNum = 0;
		iPres = 0;
		iAf = 0;
		sXlib = null;
		sYlib = null;
		bInter = false;
		iCur = -1;
		iVal = -1;
		wFont = null;
		wFontT = null;
		wFontL = null;
		rImg = null;
		zF = null;
		zF = f;
	}

	public void setNan(double i) {
		iNan = i;
	}

	public void setRotate(boolean b) {
		Rotate = b;
	}

	public boolean getRotate() {
		return Rotate;
	}

	public void setUnit(String s) {
		if (s == null || s.length() < 2) {
			iUnit = 1.0D;
			sUnit = "";
		} else {
			int i = s.indexOf(':');
			if (i > -1)
				try {
					iUnit = Double.valueOf(s.substring(0, i)).doubleValue();
					sUnit = s.substring(i + 1);
				} catch (Exception exception) {
				}
		}
	}

	public void setLg(int iC) {
		iLg = iC;
	}

	public void setBkColor(int iC) {
		cBack = iC;
	}

	public void setOrigine(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	public void setBkImage(Image img) {
		bgimg = img;
	}

	public void setFont(String fName, int fStyle, int fSize) {
		wFont = new Font(fName, fStyle, fSize);
	}

	public void setFont(Font f, char a) {
		if (a == 'L')
			wFontL = f;
		if (a == 'T')
			wFontT = f;
		if (a == 'A') {
			wFont = f;
			wFontT = f;
			wFontL = f;
		}
	}

	public void clearData() {
		iCol = 1;
		iLg = 1;
		iMax = 0.0D;
		iMin = 0.0D;
		iMax2 = 0.0D;
		iMin2 = 0.0D;
		iNum = 0;
		iCur = 0;
		iVal = -1;
		iHT = 0;
		iLT = 0;
		iUnit = 1.0D;
		sUnit = "";
		df1 = new DecimalFormat("#0");
		sTitle = new String("");
		v.removeAllElements();
		sXlib = null;
		sYlib = null;
	}

	public void setTitle(String s) {
		if (s != null) {
			sTitle = new String(s);
			iHT = 20;
		}
	}

	public void setLegend(String sX, String sY) {
		if (sX != null)
			sXlib = new String(sX);
		else
			sXlib = null;
		if (sY != null)
			sYlib = new String(sY);
		else
			sYlib = null;
	}

	public void setMinMax(double dMin, double dMax) {
		if (dMax != iNan)
			iMax = dMax / iUnit;
		if (dMin != iNan)
			iY = dMin / iUnit;
		sMax = true;
	}

	public void setCol(int k) {
		if (iCol > 0)
			iCol = k;
		else
			iCol = 1;
		cC = new Color[iCol];
		sT = new String[iCol];
	}

	public int getCol() {
		return iCol;
	}

	public boolean addCol(int i, int c, String s) {
		if (i >= iCol)
			return false;
		cC[i] = new Color(c);
		sT[i] = new String(s);
		if (wFontT == null)
			wFontT = wFont;
		i = zF.getFontMetrics(wFontT).stringWidth(s);
		if (i > iLT)
			iLT = i;
		return true;
	}

	public void setDec(int ix) {
		String s = "0";
		if (ix > 0)
			s = String.valueOf(String.valueOf(s)).concat(".");
		for (int i = 1; i < ix; i++)
			s = String.valueOf(String.valueOf(s)).concat("#");

		setDec(s);
	}

	public void setDec(String s) {
		df1 = new DecimalFormat(s);
	}

	public void setPress(int i) {
		iPres = i;
		iMax = -1.7976931348623157E+308D;
		iMin = 1.7976931348623157E+308D;
		iY = 1.7976931348623157E+308D;
		sMax = false;
		for (Enumeration e = v.elements(); e.hasMoreElements(); setMax((rq) e
				.nextElement()))
			;
		if (iMin == iMax)
			iMax = iMin + (double) 1;
		if (iY == 1.7976931348623157E+308D)
			iY = 0.0D;
		if (iMin < (double) 0 && iMax > (double) 0)
			iY = 0.0D;
	}

	public void setXPress(boolean b1, boolean b2, boolean b3) {
		iAf = 3;
		if (b1)
			iAf -= 2;
		if (b2)
			iAf--;
		bInter = b3;
	}

	public void setVal(int iV) {
		if (iV < 2)
			iVal = iNum;
		else
			iVal = iV;
	}

	private void setMax(rq s) {
		double iTmin = 1.7976931348623157E+308D;
		double iTmax = -1.7976931348623157E+308D;
		double iTy = 1.7976931348623157E+308D;
		if (iPres == 7) {
			double iTmin2 = 1.7976931348623157E+308D;
			double iTmax2 = -1.7976931348623157E+308D;
			int iCC = iCol / 3;
			for (int i = 0; i < iCC; i++) {
				if (s.iD[i] == iNan)
					continue;
				if (s.iD[i * 3] > iTmax2)
					iTmax2 = s.iD[i * 3];
				if (s.iD[i * 3] < iTmin2)
					iTmin2 = s.iD[i * 3];
				if (s.iD[i * 3 + 1] > iTmax)
					iTmax = s.iD[i * 3 + 1];
				if (s.iD[i * 3 + 1] < iTmin)
					iTmin = s.iD[i * 3 + 1];
				if (s.iD[i * 3 + 1] > (double) 0
						&& (s.iD[i * 3 + 1] < iTy || iTy == (double) 0))
					iTy = s.iD[i * 3 + 1];
			}

			if (iTmax > iMax)
				iMax = iTmax * 1.1000000000000001D;
			if (iTmin < iMin)
				iMin = iTmin;
			if (iTmax2 > iMax2)
				iMax2 = iTmax2 * 1.1000000000000001D;
			if (iTmin2 < iMin2)
				iMin2 = iTmin2;
		} else {
			if (iPres == 3 || iPres == 6)
				iTmax = iTmin = 0.0D;
			for (int i = 0; i < iCol; i++) {
				if (s.iD[i] == iNan)
					continue;
				if (iPres == 3 || iPres == 6) {
					if (s.iD[i] > (double) 0)
						iTmax += s.iD[i];
					else
						iTmin += s.iD[i];
				} else {
					if (s.iD[i] > iTmax)
						iTmax = s.iD[i];
					if (s.iD[i] < iTmin)
						iTmin = s.iD[i];
				}
				if (s.iD[i] < iTy || iTy == (double) 0)
					iTy = s.iD[i];
			}

			if (iTmax > iMax)
				iMax = iTmax * 1.1000000000000001D;
			if (iTmin < iMin)
				iMin = iTmin;
		}
		if (iY == 1.7976931348623157E+308D || iY > iTy)
			iY = iTy;
	}

	public void addRow(double iV[], String sS) {
		rq s = new rq(iCol);
		s.sIt = new String(sS);
		for (int i = 0; i < iCol; i++) {
			s.iD[i] = iV[i] / iUnit;
		}
		v.addElement(s);
		iNum++;
		setMax(s);
	}

	public void setCel(int iC, int iL, double iV) {
		if (iC < iCol && iL < iNum)
			((rq) v.elementAt(iL)).iD[iC] = iV / iUnit;
		if (iV > iMax)
			iMax = iV / iUnit;
		if (iV < iMin)
			iMin = iV / iUnit;
	}

	public void paint(Graphics g, int wi, int hi, boolean bCle) {
		int z = 2;
		if (bCle)
			z = 0;
		paint(g, wi, hi, z);
	}

	public void paint(Graphics g, int wi, int hi, int bClear) {
		if (wFont == null)
			wFont = g.getFont();
		if (wFontT == null)
			wFontT = g.getFont();
		if (wFontL == null)
			wFontL = g.getFont();
		wMetrics = zF.getFontMetrics(wFont);
		w = wi;
		h = hi;
		paintCanvas(g, bClear);
	}

	private void paintCanvas(Graphics g, int bClear) {
		if (iVal < 2)
			iVal = iNum;
		int k;
		if (iCol > 1 && iAf < 2)
			k = w - (iLT + 40);
		else
			k = w - 25;
		if (bClear == 0) {
			g.setColor(new Color(cBack));
			g.fillRect(0, 0, w, h);
			ox = 0;
			oy = 0;
			if (bgimg != null) {
				if (bgWidth == -1 || bgHeight == -1) {
					bgWidth = bgimg.getWidth(zF);
					bgHeight = bgimg.getHeight(zF);
				}
				if (bgWidth != -1 && bgHeight != -1)
					for (; oy < h; oy += bgHeight)
						for (ox = 0; ox < w; ox += bgWidth)
							g.drawImage(bgimg, ox, oy, zF);

			}
		}
		if (!sMax) {
			sMax = true;
			if (iMin > (double) 0 && iY < iMax - (iY * (double) 3) / (double) 4
					|| iMax < (double) 0
					&& iY > iMin - (iY * (double) 3) / (double) 4
					|| iMin >= (double) 0 && iMax < (double) 5
					|| iMax < (double) 0 && iMin > (double) -5) {
				iY = 0.0D;
				if (iMin >= (double) 0)
					iMin = 0.0D;
				if (iMax <= (double) 0)
					iMax = 0.0D;
			} else {
				int i = (int) (iMax - iY);
				if (i > 100) {
					i = (int) (iY / (double) 100);
					iY = i * 100;
				} else if (i > 10) {
					i = (int) (iY / (double) 10);
					iY = i * 10;
				} else if (i > 2) {
					i = (int) (iY - (double) 1);
					iY = i;
				} else if (iMax - iY > 0.10000000000000001D) {
					i = (int) (iY * (double) 10);
					iY = i / 10;
				}
				sMax = true;
			}
		}
		if (iVal == 0) {
			iVal = 1;
			iMax = 10D;
		}
		if ((-iY + iMax) - iMin <= (double) 0) {
			iMin = 0.0D;
			iY = iY - (double) 1;
		}
		fPas = (double) ((h - 10 - iHT) * 8 * 10) / ((-iY + iMax) - iMin);
		ox = X;
		oy = (h - Y) + (int) ((iMin * fPas) / (double) 100);
		iP = (k - ox) / (2 * iVal);
		if (iLg < 0)
			iLg = iVal / -iLg;
		if (iP < 1)
			iP = 1;
		if (iP < 2 && iPres != 1 && iPres < 5)
			iP = 2;
		if (bClear == 0) {
			g.setFont(wFontT);
			g.setColor(Color.black);
			int i = (w - zF.getFontMetrics(wFontT).stringWidth(sTitle)) / 2;
			g.drawString(sTitle, i, 15);
			if (iCol > 1 && iPres != 4 && iAf < 2) {
				i = 0;
				do {
					if (i >= iCol)
						break;
					g.setColor(cC[i]);
					g.fill3DRect(15 + k, 30 + 25 * i, 15, 15, true);
					g.setColor(Color.black);
					g.drawString(sT[i], 35 + k, 42 + 25 * i);
					i++;
					if (iPres == 7 && i >= iCol / 3)
						i *= 4;
				} while (true);
			}
		}
		if (iPres == 2)
			paintPie(g, k, bClear);
		else
			paintBar(g, k, bClear);
		if (bClear < 2) {
			g.setFont(wFontT);
			if (iPres != 2 && iPres != 4) {
				g.setColor(Color.black);
				if (bClear == 0 && sXlib != null) {
					int i = zF.getFontMetrics(wFontL).stringWidth(sXlib);
					g.drawString(sXlib, (w - i) / 2, h - 1);
				}
				if (sYlib != null) {
					int i = zF.getFontMetrics(wFontL).stringWidth(sYlib);
					int zz = -2;
					if (bClear == 1)
						zz = k - 5 - zF.getFontMetrics(wFontL).getHeight();
					if (Rotate)
						drawRotateText(g, sYlib, 1.5707963267948966D, zz,
								(h - i) / 2, wFontT);
					else
						g.drawString(sYlib, zz + 5, 10);
				}
			}
		}
	}

	private void drawArc(Graphics g, int i, Object o, int bCr) {
		rq d = (rq) o;
		double iSum = 0.0D;
		int x = h / yC;
		int a = i / x;
		int b = i % x;
		for (int j = 0; j < iCol; j++)
			if (d.iD[j] != iNan)
				iSum += d.iD[j];

		g.setColor(Color.black);
		g.setFont(wFontL);
		g.drawString(d.sIt, 10 + a * yC, 12 + b * yC);
		g.setColor(Color.lightGray);
		g.setFont(wFont);
		int iSx = 0;
		int iSy = 0;
		int xC = 0;
		i = wMetrics.getHeight();
		g.setColor(cC[iCol - 1]);
		g.fillOval((yC * (1 + a * 6)) / 6, (yC * (1 + b * 6)) / 6,
				(yC * 2) / 3, (yC * 2) / 3);
		for (int j = 0; j < iCol; j++) {
			int c = (int) ((d.iD[j] * (double) 360) / iSum + 0.5D);
			g.setColor(cC[j]);
			g.fillArc((yC * (1 + a * 6)) / 6, (yC * (1 + b * 6)) / 6,
					(yC * 2) / 3, (yC * 2) / 3, xC, c);
			iSx = (int) (((double) yC * Math
					.cos(((double) (xC + c / 2) * 3.1415926535897931D)
							/ (double) 180)) / (double) 3);
			iSy = (int) (((double) yC * Math
					.sin(((double) (xC + c / 2) * 3.1415926535897931D)
							/ (double) 180)) / (double) 3);
			double dV = (d.iD[j] * (double) 100) / iSum;
			if ((iAf & 1) != 1 && dV > (double) 1) {
				int aL = wMetrics.stringWidth(String.valueOf(String
						.valueOf((new StringBuffer("")).append(d2s(dV)).append(
								"%")))) + 5;
				if (iSx < 0)
					iSx -= aL - 5;
				if (iSy < 0)
					iSy -= i;
				g.setColor(Color.white);
				int aX = yC * a + yC / 2 + iSx;
				int aY = (yC * b + yC / 2) - iSy;
				if (bInter)
					if (aX > (yC * (1 + a * 6)) / 6)
						aX -= aL;
					else
						aX += aL;
				g.fillRect(aX - 1, (aY - i) + 3, aL - 5, i - 2);
				g.setColor(Color.black);
				g.drawString(String.valueOf(String
						.valueOf((new StringBuffer("")).append(d2s(dV)).append(
								"%"))), aX, aY);
			}
			xC += c;
		}

	}

	private void drawPoint(Graphics g, int i, Object o) {
		rq d = (rq) o;
		int x = 0;
		int y = 0;
		int r = 1;
		int iCC = iCol / 3;
		for (int j = 0; j < iCC; j++) {
			if (d.iD[j] == iNan)
				continue;
			r = (int) d.iD[j * 3 + 2] * 5;
			x = (ox + (int) ((d.iD[j * 3] * fPas2) / (double) 100)) - r / 2;
			y = oy - (int) ((d.iD[j * 3 + 1] * fPas) / (double) 100) - r / 2;
			g.setColor(cC[j]);
			g.fillOval(x, y, r, r);
			g.setColor(Color.black);
			if ((iAf & 1) != 1) {
				int k2 = wMetrics.stringWidth(d.sIt);
				g.drawString(d.sIt, x + (r - k2) / 2, y + r + 10);
			}
			if ((1 + i) % iM == 0 && iLg > 0) {
				g.drawLine(x + r / 2, oy - 2, x + r / 2, oy + 2);
				g.drawString(d2s(d.iD[j * 3]), (x - 5) + r / 2, oy + 25);
			}
		}

	}

	private void drawBar(Graphics g, int i, Object o, int bClear) {
		rq d = (rq) o;
		int x = 0;
		int y = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		int z = 0;
		int iP1[] = new int[4];
		int iP2[] = new int[4];
		g.setFont(wFont);
		int k = wMetrics.getHeight();
		int k2 = 0;
		int ym = oy;
		int yM = oy;
		int yM2 = oy;
		int iTmp = 0;
		int pPas = (iP * 2) / (iCol + 1);
		for (int j = 1; j <= iCol; j++) {
			x = ox + (i - iCur) * 2 * iP;
			if (iPres == 0)
				x += j * pPas - pPas;
			if (d.iD[iCol - j] > (double) 0) {
				y = (int) ((double) yM - ((d.iD[iCol - j] - iY) * fPas)
						/ (double) 100);
				y2 = yM;
				iTmp = y;
				if (iPres == 3 || iPres == 6)
					yM = y;
			} else {
				y = ym;
				y2 = y + (int) ((-d.iD[iCol - j] * fPas) / (double) 100);
				iTmp = y2;
				if (iPres == 3 || iPres == 6)
					ym = y2;
			}
			if (i - iCur > 0)
				y1 = (int) ((double) yM2 - ((((rq) v.elementAt(i - 1)).iD[iCol
						- j] - iY) * fPas)
						/ (double) 100);
			if (d.iD[iCol - j] == iNan)
				continue;
			if ((iAf & 1) != 1) {
				String s2 = (new String(d2s(d.iD[iCol - j]))).trim();
				k2 = wMetrics.stringWidth(s2);
				if (iPres == 1 || iPres == 8)
					z = ox + (i - iCur) * 2 * iP;
				else
					z = ox + (i - iCur) * 2 * iP + iP;
				g.setColor(Color.white);
				g.fill3DRect(z, iTmp, k2 + 4, k - 2, true);
				g.setColor(Color.black);
				g.drawString(s2, z + 2, (iTmp + k) - 3);
				g.setColor(cC[iCol - j]);
				g.draw3DRect(z, iTmp, k2 + 4, k - 2, true);
			}
			g.setColor(cC[iCol - j]);
			if (iPres == 0 || iPres == 3) {
				if (iPres == 0)
					g.fill3DRect(x, y, pPas, y2 - y, true);
				else
					g.fill3DRect(x, y, iP, y2 - y, true);
				continue;
			}
			if (iPres == 1 || iPres == 8) {
				if (i - iCur < 0)
					continue;
				if (iPres == 1) {
					if (i - iCur <= 0)
						continue;
					x2 = (int) ((double) oy - ((((rq) v.elementAt(i - 1)).iD[iCol
							- j] - iY) * fPas)
							/ (double) 100);
					y = (int) ((double) oy - ((d.iD[iCol - j] - iY) * fPas)
							/ (double) 100);
					g.drawLine(x, y, x - iP * 2, x2);
					if (iP > 4) {
						g.drawLine(x + 1, y, (1 + x) - iP * 2, x2);
						if (!bInter)
							g.fillRect(x - 3, y - 3, 6, 6);
					}
					if (bClear != 0 || (1 + i) % iM != 0)
						continue;
					g.setColor(new Color(0xa0a0a0));
					if (y2 == y)
						g.drawLine(x, y, x, oy);
					else
						g.drawLine(x, y, x, y2);
					continue;
				}
				y = (int) ((double) oy - ((d.iD[iCol - j] - iY) * fPas)
						/ (double) 100);
				if (iP > 15) {
					g.fillRect((x - 4) + j, (y - 4) + j, 6, 6);
				} else {
					g.drawLine(x - 1, y, 1 + x, y);
					g.drawLine(x - 1, y - 1, 1 + x, y - 1);
				}
				continue;
			}
			if (iPres <= 4 || i - iCur <= 0)
				continue;
			iP1[0] = iP1[1] = x;
			iP1[2] = iP1[3] = x - iP * 2;
			iP2[0] = y2;
			iP2[1] = y;
			if (y1 > oy) {
				iP2[2] = yM2;
				iP2[3] = y1;
			} else {
				iP2[2] = y1;
				iP2[3] = yM2;
			}
			if (iPres == 6)
				yM2 = y1;
			g.fillPolygon(iP1, iP2, 4);
		}

		if ((1 + i) % iM == 0 && iLg > 0 && bClear == 0) {
			g.setColor(Color.black);
			g.drawLine(ox + (i - iCur) * 2 * iP, oy - 2, ox + (i - iCur) * 2
					* iP, oy + 2);
			if (Rotate) {
				drawRotateText(g, String.valueOf(String.valueOf(d.sIt)).concat(
						" "), 1.5707963267948966D, (ox - 10) + (i - iCur) * 2
						* iP, (oy + k) - 11, wFontL);
			} else {
				g.setFont(wFontL);
				g.drawString(d.sIt, (ox - 5) + (i - iCur) * 2 * iP, oy + k
						* (1 + (i - iCur) % 2));
			}
		}
	}

	private void paintPie(Graphics g, int k, int bClear) {
		int j = 0;
		yC = (int) Math.sqrt((h * k) / iNum);
		j = h / yC;
		int a2 = k / yC;
		if (a2 == 0)
			a2 = 1;
		int i;
		for (i = j * a2; i < iNum; i = j * a2)
			if (h >= k && (h * a2) / k < j || k > h && (k * j) / h < a2) {
				j++;
				yC = h / j;
			} else {
				a2++;
				yC = k / a2;
			}

		g.setColor(Color.black);
		g.setFont(wFont);
		i = 0;
		for (Enumeration e = v.elements(); e.hasMoreElements(); drawArc(g, i++,
				e.nextElement(), bClear))
			;
	}

	private void paintBar(Graphics g, int k, int bClear) {
		double j = 0.0D;
		double iG = 1.0D;
		ox = X;
		oy = (h - Y) + (int) ((iMin * fPas) / (double) 100);
		if (iPres == 7) {
			fPas2 = (double) ((w - 30) * 8 * 10) / (iMax2 - iMin2);
			ox = 30 - (int) ((iMin2 * fPas2) / (double) 100);
		}
		if (iVal + iCur >= iNum)
			iScroll = 0;
		else
			iScroll = 1;
		if (iP < 4) {
			if (iVal > 200)
				iM = 25;
			else
				iM = 10;
		} else {
			iM = 1;
		}
		if (iLg > 1)
			iM = iLg;
		double i;
		if (iMax > (double) 0) {
			i = (iMax - iY) / (double) 10;
			if (iMin < (double) 0 && iMax + iMin < (double) 0)
				i = (iMin - iY) / (double) 10;
		} else {
			i = (iMin - iY) / (double) 10;
		}
		for (i = Math.abs(i); iG < i; iG *= 10)
			;
		double zz = iMax - iY;
		if (iMax <= (double) 0)
			zz = iMin - iY;
		else if (iMin < (double) 0)
			zz = iMax - iMin;
		for (; Math.abs(zz / iG) < (double) 4; iG /= 2)
			;
		int z = 0;
		i = oy;
		for (j = 0.0D; i < (double) (h - 50); j -= iG)
			i += (iG * fPas) / (double) 100;

		g.setFont(wFontL);
		if (bClear < 2) {
			while (i > (double) 15) {
				String s2 = d2s(j + iY);
				int ii = ox - 5 - wMetrics.stringWidth(s2);
				if (bClear == 0) {
					g.setColor(new Color(0xa0a0a0));
					g.drawLine(ox, (int) i, k, (int) i);
				} else if (k == w - 25)
					ii = w - 2 - wMetrics.stringWidth(s2);
				else
					ii = k + 5;
				g.setColor(Color.black);
				g.drawString(s2, ii, (int) (i + (double) 2));
				i -= (iG * fPas) / (double) 100;
				j += iG;
			}
			g.setColor(Color.black);
			if (bClear == 1)
				g.drawLine(k, 20, k, h - 30);
			else
				g.drawLine(ox, 20, ox, h - 30);
			g.drawLine(ox - 5, oy, k, oy);
		}
		z = 0;
		for (Enumeration e = v.elements(); e.hasMoreElements(); z++) {
			if (iPres == 7) {
				drawPoint(g, z, e.nextElement());
				continue;
			}
			if (z >= iCur && z < iCur + iVal)
				drawBar(g, z, e.nextElement(), bClear);
			else
				e.nextElement();
		}

	}

	private String d2s(double d) {
		String s = String.valueOf(String.valueOf((new StringBuffer(String
				.valueOf(String.valueOf(df1.format(d))))).append(" ").append(
				sUnit)));
		return s.trim();
	}

	private void drawRotateText(Graphics g, String txt, double angle, int X,
			int Y, Font f) {
		FontMetrics w = zF.getFontMetrics(f);
		if (rImg == null) {
			try {
				rImg = zF.createImage(h, 150);
				rGr = rImg.getGraphics();
			} catch (Exception e) {
				rImg = null;
				return;
			}
			rGr.setFont(wFont);
		}
		rGr.setFont(f);
		rGr.setColor(new Color(cBack));
		rGr.fillRect(0, 0, h, 50);
		rGr.setColor(Color.black);
		rGr.drawString(txt, 1, w.getHeight());
		ImageFilter filter = new RotateFilter(angle);
		ImageProducer producer = new FilteredImageSource(rImg.getSource(),
				new CropImageFilter(0, 0, w.stringWidth(txt), w.getHeight()));
		Image resultImage = zF.createImage(new FilteredImageSource(producer,
				filter));
		g.drawImage(resultImage, X, Y, zF);
	}

	double iNan;
	private boolean Rotate;
	private Vector v;
	private DecimalFormat df1;
	private int cBack;
	private int w;
	private int ox;
	private int iHT;
	private int iLT;
	private int X;
	private int Y;
	private int iLg;
	private int h;
	private int oy;
	private int iP;
	private double iMin;
	private double iMax;
	private double iMin2;
	private double iMax2;
	private double iY;
	private int bgWidth;
	private int bgHeight;
	private double fPas;
	private double fPas2;
	private int iCol;
	private boolean sMax;
	private double iUnit;
	private String sUnit;
	private int xC;
	private int yC;
	private int iM;
	private int iNum;
	private int iPres;
	private int iAf;
	private Color cC[];
	private String sT[];
	private String sTitle;
	private String sXlib;
	private String sYlib;
	private boolean bInter;
	private int iCur;
	private int iScroll;
	private int iVal;
	private Font wFont;
	private Font wFontT;
	private Font wFontL;
	private FontMetrics wMetrics;
	private Image bgimg;
	private Image rImg;
	private Graphics rGr;
	Frame zF;
}
