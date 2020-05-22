import datetime
import time
from bs4 import BeautifulSoup
from selenium import webdriver
import pymysql as my

# datsstock table을 매일 업데이트하는 프로그램


conn=my.connect(host='192.168.56.66',user='taekyoung',password='1234',db='kospiece_ex2',charset='utf8')
cur=conn.cursor(my.cursors.DictCursor)

# 삽입쿼리
sql='''insert into datestock(sno,ddate,dstart,dhigh,drow,dend)
        values(%s,%s,%s,%s,%s,%s)'''


now = datetime.datetime.now()
ddate = now.strftime('%Y-%m-%d')

driver = webdriver.Chrome('chromedriver.exe')
url = 'https://finance.daum.net/domestic/kospi200'
driver.get(url)
for k in range(0,3,1):
    for j in range(0,10,1):
        html = driver.page_source
        # soup = BeautifulSoup(html, 'html.parser')
        for i in range(0, 10):
            if k >= 1:
                btn2 = driver.find_element_by_css_selector('#boxEntryChange > div.box_contents > div > div > .btnNext')
                btn2.click()
                time.sleep(0.7)
                if k == 2:
                    btn2 = driver.find_element_by_css_selector(
                        '#boxEntryChange > div.box_contents > div > div > .btnNext')
                    btn2.click()
                    time.sleep(0.7)
            if j!=0:
                btn = driver.find_elements_by_css_selector('#boxEntryChange > div.box_contents > div > div > .btnMove')
                btn[j-1].click()
                time.sleep(0.7)
            #기업클릭
            comf = driver.find_element_by_css_selector('#boxEntryChange > div.box_contents > div > table > tbody > tr:nth-child(%s) > th > a'%(i+1)).text
            print(comf)
            if comf =="우리은행":   #우리은행 정보없음
                driver.back()
                time.sleep(0.5)
                driver.forward()
                time.sleep(0.5)
                continue
            link=driver.find_elements_by_css_selector('#boxEntryChange > div.box_contents > div > table > tbody > tr > th > a')
            link[i].click()
            time.sleep(1)

            # 기업이름
            com_link = driver.find_element_by_css_selector('#favorite > em')
            sno = com_link.text
            print(sno)

            #현재가버튼클릭
            nowprice_link=driver.find_element_by_css_selector('#boxTabs > td:nth-child(2) > a')
            nowprice_link.click()
            time.sleep(0.5)

            #시가 고가 저가 종가
            aaa = driver.find_element_by_css_selector('#boxDayHistory > div > div.box_contents > div > table > tbody > tr.first > td:nth-child(2) > span')
            dstart=aaa.text.replace(',','')
            bbb = driver.find_element_by_css_selector('#boxDayHistory > div > div.box_contents > div > table > tbody > tr.first > td:nth-child(3) > span')

            dhigh=bbb.text.replace(',','')
            ccc = driver.find_element_by_css_selector(
                '#boxDayHistory > div > div.box_contents > div > table > tbody > tr.first > td:nth-child(4) > span')
            drow=ccc.text.replace(',','')
            ddd = driver.find_element_by_css_selector(
                '#boxDayHistory > div > div.box_contents > div > table > tbody > tr.first > td:nth-child(5) > span')
            dend=ddd.text.replace(',','')
            dstart=int(dstart)
            dhigh=int(dhigh)
            drow=int(drow)
            dend=int(dend)
            # print(ddate,comf, dstart, dhigh,drow,dend)
            # print(comf,aaa,bbb,ccc,ddd)
            # 삽입실행
            cur.execute(sql, (sno,ddate,dstart,dhigh,drow,dend))

            # 수정실행
            # cur.execute(sql, (company,field,field_WICS,jno,company))

            driver.back()
            driver.back()
            time.sleep(1)
            if k==2:
                break
        if k==2:
            break

#
conn.commit()
conn.close()