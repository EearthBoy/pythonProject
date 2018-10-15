import re
import requests
from  bs4 import BeautifulSoup
import sys
#拿到所有章节的url
def get_url(url,header):
    #定义字典
    xiaoshuo={}
    #得到网页源代码
    url_text=requests.get(url,header).text
    #解决编码问题
    url_text=url_text.encode('ISO-8859-1').decode('utf-8')
    #拿到a标签

    soup=BeautifulSoup(url_text,'lxml').find_all('a')
    #拿到a标签的url
    for string in soup:
        string=string.__str__()
        manage=re.match(r'(.*)(<a href=")(.*)(">)(第.*章.*)(</a>)',string)
        if manage:
            xiaoshuo[manage.group(5)]=manage.group(3)
    return  xiaoshuo
def dowload(name,url,dir):
    #得到小说的内容
    req=requests.get(url).text.encode('ISO-8859-1').decode('utf-8')
    soup=BeautifulSoup(req,'lxml').find_all('div',{'id':'content'})[0]
    #存入小说
    xiaoshuo=[]
    for string in soup:
        st = string.__str__()
        print(st)

        if (len(st.split('<br/>')) > 1):
            pass
        else:
            xiaoshuo.append(st)
    print(xiaoshuo)
    #打开文件对象，创建对象
    file=dir+name+".txt"
    file=open(file,mode='w+', encoding='utf-8')
    for line in xiaoshuo:
        file.write('\t')
        for ii in line.split('    '):
            file.write(ii)
        file.write('\n')

if __name__ == '__main__':
    #仙逆小说主页
    a_url="http://www.shuquge.com/txt/74671/index.html"
    #主url 进行拼接
    main_url='http://www.shuquge.com/txt/74671/'
    headr={
        'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36'
    }
    #下载到哪个文件夹
    dir='/Users/caozhan/Desktop/xianni/'
    xiaoshuo=get_url(a_url,headr)
    for key,value in xiaoshuo.items():
        #拿到章节
        name=key
        #拼接字符串来达到完整的url
        url=main_url+value
        #调用下载
        dowload(name,url,dir)

