import time
import requests as rq
from bs4 import BeautifulSoup as bs
import pymysql as my

# 차트를 제외한 주식 변수들을 업데이트 크롤링 쿼리문
# 업종,세부업종,코드넘버 (상수들) 제외
# 네이버로 함


conn=my.connect(host='192.168.56.66',user='taekyoung',password='1234',db='kospiece_ex2',charset='utf8')
cur=conn.cursor(my.cursors.DictCursor)

# 삽입쿼리
# sql='''insert into kospi200(company,price,dayRate,changeRate,amount,dealPrice,total,price52)
#         values(%s,%s,%s,%s,%s,%s,%s,%s)'''

# 수정쿼리
sql='''update Stock
        set sname=%s, sprice=%s, sdayrate=%s, schangerate=%s, svolume=%s, sdealprice=%s, stotal=%s
        where sname=%s'''

for i in range(1,21):
    url="https://finance.naver.com/sise/entryJongmok.nhn?&page="+str(i)
    url=rq.get(url)
    dom=bs(url.text,'html.parser')

    tds=dom.find_all('td')
    for j in range(0,10):
        c=j*7
        for k in range(1,2):
            d=c+k
            sname=tds[d].text
            sprice=int((tds[d+1].text).replace(',',''))
            sdayrate=tds[d+2].text.strip()
            schangerate=tds[d+3].text.strip()
            svolume=tds[d+4].text
            sdealprice=tds[d+5].text
            stotal=tds[d+6].text

            stotal=stotal.replace(",","")
            stotal=int(stotal)
            sdayrate=sdayrate.replace(",","")
            schangerate = schangerate.replace("%", "")
            if schangerate[0]=='-':
                sdayrate = '-' + sdayrate
            schangerate=float(schangerate)
            print(sname, sprice, sdayrate, schangerate, svolume, sdealprice, stotal)
            #수정실행문
            cur.execute(sql, (sname, sprice, sdayrate, schangerate, svolume, sdealprice, stotal, sname))

conn.commit()
conn.close()