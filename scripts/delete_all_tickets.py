import requests


resp = requests.get('http://localhost:8080/tickets')
lst = resp.json()
for el in lst:
    hsh = el['uniqueHash']
    requests.delete(f"http://localhost:8080/tickets/{hsh}")
