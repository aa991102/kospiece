import time

from bs4 import BeautifulSoup
from selenium import webdriver
import pymysql as my
#업종,WICS업종,주식코드번호 수집하기위한 파이썬
conn=my.connect(host='192.168.56.66',user='taekyoung',password='1234',db='project',charset='utf8')
cur=conn.cursor()
#
#삽입쿼리
# sql='''insert into kospi200(company,field,field_WICS)
#         values(%s,%s,%s)'''

#수정쿼리
sql='''update kospi200
        set company=%s, field=%s, field_WICS=%s, jno=%s
        where company=%s'''
driver = webdriver.Chrome('chromedriver.exe')
#
url = 'https://finance.daum.net/domestic/kospi200'
#
driver.get(url)
number=0
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
                time.sleep(0.7)
                driver.forward()
                time.sleep(0.7)
                continue
            link=driver.find_elements_by_css_selector('#boxEntryChange > div.box_contents > div > table > tbody > tr > th > a')
            link[i].click()
            time.sleep(1)

            #기업이름
            com_link = driver.find_elements_by_css_selector('#boxDashboard > span > div > span:nth-child(1) > span:nth-child(1) >span:nth-child(1)')
            company=com_link[0].text[1:-6]
            jno=com_link[0].text[-6:]


            #업종,WICS
            kind = driver.find_element_by_css_selector(
                '#boxDashboard > div > div > span.txtB > dl > dd:nth-child(6) > p')
            field = kind.text
            kind_WICS = driver.find_element_by_css_selector(
                '#boxDashboard > div > div > span.txtB > dl > dd:nth-child(11) > p > cite')
            field_WICS = kind_WICS.text

            number+=1
            print(number, company, field,field_WICS,jno,comf)

            # 삽입실행
            # cur.execute(sql, (company,field,field_WICS))

            # 수정실행
            cur.execute(sql, (company,field,field_WICS,jno,company))

            driver.back()
            time.sleep(1)
            if k==2:
                break
        if k==2:
            break

#
conn.commit()
conn.close()