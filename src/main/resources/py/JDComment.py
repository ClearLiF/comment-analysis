# -*- coding: UTF-8 -*-
import requests as rq
from bs4 import BeautifulSoup
import re
import json

def hello():
    print('hello')
    return "okkkk"

def get(keyword, type, size):
    text = search(keyword)
    items = get_items(text)
    d = {}
    sum = 0
    for id in items:
        page = 0
        comments = []
        count = 0
        while count < size:
            comment = get_comment(id, type, page)
            comments.extend(comment)
            count += 10
            page += 1
            sum += 10
        d[items[id]] = comments
    return d


def get_html_text(url, params=None):
    user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36"
    headers = {"User-Agent": user_agent}
    try:
        r = rq.get(url, params=params, headers=headers)
        r.raise_for_status()
        r.encoding = r.apparent_encoding
        return r.text
    except:
        return "获取网页内容异常"


def search(keyword):
    url = "https://search.jd.com/Search"
    # 搜索结果按评论数倒排
    params = {"keyword": keyword, "psort": "4"}
    return get_html_text(url, params=params)


def get_items(text):
    soup = BeautifulSoup(text, "html.parser")
    attrs = {'class': 'p-name p-name-type-2'}
    items = {}
    for div in soup.find_all(attrs=attrs):
        try:
            title = div.find('em').get_text().split('\n')
            name = title[len(title) - 1]
            href = div.find_all('a')[0].get('href')
            item = re.findall(r"//item.jd.com/(.*)\.html", href)[0]
            items[item] = name
        except:
            continue
    return items


# type: 1/差评;2/中评;3/好评;
def get_comment(productId, type, page):
    url = "https://club.jd.com/comment/productPageComments.action"
    params = {
        "productId": productId,
        "score": type,
        "sortType": 5,
        "page": page,
        "pageSize": 10
    }
    json_str = get_html_text(url, params=params)
    content = []
    try:
        comments = json.loads(json_str)
        for comment in comments['comments']:
            content.append(comment['content'])
    except:
        pass
    return content
