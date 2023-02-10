import requests

import json

list_ids=['RDCLAK5uy_l7wbVbkC-dG5fyEQQsBfjm_z3dLAhYyvo','RDCLAK5uy_nMqMWmqwZwDvMcqcAohgO_Dp_7zT1nSC8', 'RDCLAK5uy_m9ty3WvAucm7-5KsKdro9_HnocE8LSS9o','RDCLAK5uy_kRRqnSpfrRZ9OJyTB2IE5WsAqYluG0uYo','RDCLAK5uy_lYPvoz4gPFnKMw_BFojpMk7xRSIqVBkEE','RDCLAK5uy_l6DCR35xfT9bfeUqP7-uw6kWApcfYeDPY','RDCLAK5uy_k6pZ82Gha0sopanWffXo4iMBVaGR7jQaE','RDCLAK5uy_mMRkzfvFXzNQbSl3K-hE_FJ7g8TqMtSlo','RDCLAK5uy_mjCKq8hnUQJqul0W6YW6x2Ep4P67jQ5Po','RDCLAK5uy_lBfTSdVdgXeN399Mxt2L3C6hLarC1aaN0','PL4fGSI1pDJn6jXS_Tv_N9B8Z0HTRVJE0m','PL4fGSI1pDJn5S09aId3dUGp40ygUqmPGc','PL4fGSI1pDJn6puJdseH2Rt9sMvt9E2M4i','PL4fGSI1pDJn5kI81J1fYWK5eZRl1zJ5kM','RDCLAK5uy_mA88hxo-cmI0-WaaRH8Bb2k0x2NptOPqM','RDCLAK5uy_meEBX-iIBwtXBhkeWzwX6njohWnpMijP8','RDCLAK5uy_kT-sIJz2O-hpkxwjosN2hMt9Y5xevcPYI','RDCLAK5uy_lNJA7PB9DAQEdtTnfuKaC2XEOAE1OoX50','RDCLAK5uy_lv6V83HLaJMQDx8YFtfSAaZ6GGvSqI6PE','RDCLAK5uy_mL0lDqxdKwRtBbzva3yVjVy-BZ9L7KX5I','RDCLAK5uy_kDBL_tFOUos7q3SOifZrMHXKwuebdzf7I','RDCLAK5uy_nSXFJbOcdpwmPeYjR7XxOT0W5s_GSYqBQ','RDCLAK5uy_kjKtb_RC7LRbxiEmSIzZqJRVcYm8U9KMc','RDCLAK5uy_nEimEUlO50cs4tJlxCnOe-yvM-17ZZAlI','RDCLAK5uy_nYQVLFnQcRRKrwv2Z8uZTgQgRLMze-2to','RDCLAK5uy_nYQVLFnQcRRKrwv2Z8uZTgQgRLMze-2to','RDCLAK5uy_ncBNbvnVAw5s59uypQPpYXpWZ81xtBBdQ','RDCLAK5uy_nIRgGCv8-e7YuKIc9WFyuz9cGyrM1P4Rg','RDCLAK5uy_lJ_8Yzoj3LEEzFtO3JXiD0OkwCV4aAKko','RDCLAK5uy_ltlzGKYlRekORMvbjNz7WRE_LHcWiBAGY','RDCLAK5uy_khzJMzOMfZAw7x0dswMTl8QVBgr-yKnVw']
list_ids=list(set(list_ids))
result=[]
for id in list_ids:
    url=f'https://www.googleapis.com/youtube/v3/playlistItems?part=snippet,contentDetails&playlistId={id}&key=AIzaSyD2CGaAoiwMW8gYOR9ho2nHNiTCKgkItHg&maxResults=50'
    pass
    res=requests.get(url)

    jsonData=res.json()

    videos=jsonData['items']
    song_no=len(videos)
    print(id)
    for song in range(song_no):
        # print(song)
        try:
            song_img=videos[song]['snippet']['thumbnails']['default']['url']
        except:
            continue
        try:
            song_title=videos[song]['snippet']['title']
        except:
            continue
        singer='unfound'
        try:
            song_url='https://www.youtube.com/watch?v='+videos[song]['contentDetails']['videoId']
        except:
            continue
    
        video={'SONG-IMG':song_img, 'SONG_TITLE':song_title,'SINGER':singer ,'SONG_URL':song_url }
        if video not in result:
            result.append(video)
    
    try:
        if jsonData['nextPageToken']:
            token=jsonData['nextPageToken']
        
            newUrl=url+'&pageToken='+token
            res2=requests.get(newUrl)
            jsonData2=res2.json()
            videos=jsonData2['items']
            song_no2=len(videos)
            for song2 in range(song_no2):
                song_img=videos[song2]['snippet']['thumbnails']['default']['url']
                song_title=videos[song2]['snippet']['title']
                singer='unfound'
                song_url='https://www.youtube.com/watch?v='+videos[song2]['contentDetails']['videoId']
            
                video={'SONG-IMG':song_img, 'SONG_TITLE':song_title,'SINGER':singer ,'SONG_URL':song_url }
                if video not in result:
                    result.append(video)
    except:
        pass
        
for i in range(len(result)):
    result[i]['SONG_ID']=i
    
with open('song_data3.json', 'w', encoding='UTF-8') as m:
    json.dump(result, m, indent=2 ,ensure_ascii=False)