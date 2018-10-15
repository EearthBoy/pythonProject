import  requests
import urllib.parse
import re
import urllib.request
import os
from tqdm import tqdm
import ssl
ssl._create_default_https_context = ssl._create_unverified_context

#拿到img的url
def get_url_img(img_name,list_img,index_start):
    header={
        'User-Agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36',
    }
    #转换成url的形式
    img_name=urllib.parse.quote(img_name)
    #通过urlib.parse.quote将img_name转换成url的地址
    url="https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&fp=result&oe=utf-8&word="+img_name+"pn="+str(index_start)+"&rn=30"
    #将拿到的url进行编码，防止乱码
    url_text=requests.get(url,header).text
    #通过查看网页源代码拿到.jps的url
    img_list=re.findall(r'"ObjURL":"(.*?)",',url_text,re.S)
    for img_url in img_list:
        list_img.append(img_url)
    return list_img


#下载图片
def download_img(img_url_list,save_path):
    index=0;
    for img_url in tqdm(img_url_list):
        save_path1=save_path+str(index)+".jpg"
        urllib.request.urlretrieve(img_url,save_path1)
        index+=1

#对下载路径进行判断
def save_path_chuli(save_path):
    if os.path.exists(save_path):
        if str(save_path).endswith("/"):
            return  save_path
        else:
            return save_path+"/"
    else:
        os.mkdir(save_path)
        return save_path+"/"

#开始任务
def start():
    img_name = input("请输入你要查询的图片\n")
    save_path = input("请输入保存的路径\n")
    #对下载路径进行设置
    save_path=save_path_chuli(save_path)
    # 保存图片的路径
    list_img = []
    print("开始获取图片url")
    for i in tqdm(range(40)):
        index_start=i*30
        if len(list_img)==0:
            list_img=get_url_img(img_name,list_img,index_start)
        else:
            list_img=get_url_img(img_name,list_img,index_start)
    print("图片获取完成，共" + str(len(list_img)) + "张图片--开始下载")
    # download_img(list_img, save_path)
    print("下载完成")
    for i in list_img:
        print(i)

if __name__ == '__main__':
    start()
