import time
from bs4 import BeautifulSoup
from selenium import webdriver
import pymysql as my

# 차트를 제외한 주식 변수들을 업데이트 크롤링 쿼리문
# 업종,세부업종,코드넘버 (상수들) 제외


conn=my.connect(host='192.168.56.66',user='taekyoung',password='1234',db='project',charset='utf8')
cur=conn.cursor()

# 삽입쿼리
# sql='''insert into kospi200(company,price,dayRate,changeRate,amount,dealPrice,total,price52)
#         values(%s,%s,%s,%s,%s,%s,%s,%s)'''

# 수정쿼리
sql='''update kospi200
        set company=%s, price=%s, dayRate=%s, changeRate=%s, amount=%s, dealPrice=%s, total=%s, price52=%s
        where company=%s'''

driver = webdriver.Chrome('chromedriver.exe')

url = 'https://finance.daum.net/domestic/kospi200'

driver.get(url)
number=1
for k in range(0,3,1):
    for j in range(0,10,1):
        html = driver.page_source
        soup = BeautifulSoup(html, 'html.parser')
        div = soup.find("div", {"id": "boxEntryChange"})
        tr = div.find_all('tr')
        for i in range(1, len(tr)):
            company = tr[i].find('th').text
            com = tr[i].find_all('td')
            price = com[0].text
            dayRate = com[1].text
            changeRate = com[2].text
            amount = com[3].text
            dealPrice = com[4].text
            total = com[5].text
            price52 = com[6].text

            total=total.replace(",","")
            total=int(total)
            changeRate = changeRate.replace("%", "")
            changeRate=float(changeRate)

            # 프롬프트 확인
            print(number,company, price, dayRate, changeRate, amount, dealPrice, total, price52)
            number=number+1

            # 삽입실행문
            # cur.execute(sql, (company, price, dayRate, changeRate, amount, dealPrice, total, price52))

            #수정실행문
            cur.execute(sql, (company, price, dayRate, changeRate, amount, dealPrice, total, price52, company))

            if k == 2:
                break
        if k == 2:
            break
        if j<9:
            btn = driver.find_elements_by_css_selector('#boxEntryChange > div.box_contents > div > div > .btnMove')
            btn[j].click()
            time.sleep(0.7)
        if j==9:
            btn2 = driver.find_element_by_css_selector('#boxEntryChange > div.box_contents > div > div > .btnNext')
            btn2.click()
            time.sleep(0.6)
conn.commit()
conn.close()