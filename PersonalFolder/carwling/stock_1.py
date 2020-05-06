import time
from bs4 import BeautifulSoup
from selenium import webdriver
import pymysql as my

# 차트를 제외한 주식 변수들을 업데이트 크롤링 쿼리문
# 업종,세부업종,코드넘버 (상수들) 제외


conn=my.connect(host='192.168.56.66',user='taekyoung',password='1234',db='kospiece_ex2',charset='utf8')
cur=conn.cursor()

# 삽입쿼리
# sql='''insert into kospi200(company,price,dayRate,changeRate,amount,dealPrice,total,price52)
#         values(%s,%s,%s,%s,%s,%s,%s,%s)'''

# 수정쿼리
sql='''update Stock
        set sname=%s, sprice=%s, sdayrate=%s, schangerate=%s, svolume=%s, sdealprice=%s, stotal=%s, shigh52=%s
        where sname=%s'''

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
            sname = tr[i].find('th').text
            com = tr[i].find_all('td')
            sprice = com[0].text
            sdayrate = com[1].text
            schangerate = com[2].text
            svolume = com[3].text
            sdealprice = com[4].text
            stotal = com[5].text
            shigh52 = com[6].text

            stotal=stotal.replace(",","")
            stotal=int(stotal)
            sdayrate=sdayrate.replace("▲", "")
            sdayrate=sdayrate.replace("▼", "-")
            sdayrate=sdayrate.replace(",","")
            sprice = sprice.replace(",", "")

            schangerate = schangerate.replace("%", "")
            schangerate=float(schangerate)

            # 프롬프트 확인
            print(number,sname, sprice, sdayrate, schangerate, svolume, sdealprice, stotal, shigh52)
            number=number+1

            # 삽입실행문
            # cur.execute(sql, (company, price, dayRate, changeRate, amount, dealPrice, total, price52))

            #수정실행문
            cur.execute(sql, (sname, sprice, sdayrate, schangerate, svolume, sdealprice, stotal, shigh52, sname))

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