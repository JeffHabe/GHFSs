# -*- coding: utf-8 -*-
"""
Created on Mon Oct 15 00:29:25 2018

@author: Jeff PC
"""

# -*- coding: utf-8 -*-
"""
Created on Thu May  3 16:27:43 2018

@author: Jeff
"""
import readList as rdList
import os.path as pth 
import numpy as np
import math
import pandas as pd 
import cufflinks as cf
import plotly.graph_objs as go
import folderTool as fT
import plotTool as pltT
import mathTool as mthT
import time
import datetime as dt
import datetime

#mypath ='excelFolder/' #原始數據
mypath='D://PythonWorkSpace//PyExp//Data_csv//RAW//' #整理後數據位置
cf.go_offline()

#==============================================================================
def compR(Nc,Nr):
    #print(Nc,Nr)
    Nr=Nr*2*8
    Nc=Nc*(pIndex+4)*8
    comp_ratio=(Nr-Nc)/Nr
    return round(comp_ratio,2)
def calRMSE(data,f): #計算RMSE//reture RMSE 平均值
    rmseCpData=[]
    mRMSD=0
    sumData=0
# =============================================================================
#     print(len(tdata))
#     print(len(dData))
# =============================================================================
    try:
        for n in range(len(data)):
        ## to compare decompress Alg data and Decompress DB data
            sumData+=(np.round(data[n],5)-np.round(f[n],5))**2
        rmseCpData.append(float(np.round(math.sqrt(sumData/len(data)),5)))
        mRMSD=round(np.mean(rmseCpData),3)
    except Exception as e:
        print(e,"in calRMSE()")
        
    return mRMSD


# =============================================================================
# def getRnSData(SGwd_length,SGpolyOrd):
#     for i in range(len(data)):
#         t.append(i)
#         v.append(float(data[i]))
#     sgf=savgol_filter(data,SGwd_length,SGpolyOrd)
#     R2_RnS=round(mthT.coeff_of_determination(np.array(data),sgf),3)
#     global pltData
#     #print(times)
#     pltData += [
#         go.Scatter(
#             x=plotTime, # assign x as the dataframe column 'x'
#             y=v,
#             name='Raw',
#             mode='lines',
#             marker=dict(
#                     size=10,
#                     color='rgba(104,103,103,0.8)'),
#                     line=dict(width=5,)
# =============================================================================
# ===========================================================================
#                     ),
#         go.Scatter(        
#             x=times, # assign x as the dataframe column 'x'
#             y=sgf,
#             name='S-Gf: R2:'+str(R2_RnS),
#             mode='line',
#             marker=dict(
#                     size=5,
#                     color='rgba(0,0,0,0.0)'),
#                     line=dict(
#                             width=3,
#                             color='rgba(0,0,0,0.0)')
# ===========================================================================
# =============================================================================
#            )]
#     return sgf
# =============================================================================

#==============Seg2Poly==========
def CHEB_cod(fileName,
            min_r2=0.95,
            polyIndex=1,
            window_size_percent=0.5,
            maxCv=0.01,
            isplot=False):
# ==========================
# 設定初始、結束位置等
# ==========================
    startPt=0
    endPt=0
    delta=0
    timer=0

    '''
    plot(t,v,'-')
    plot(t,sgf,'-')
    '''
    tStart = time.time()#計時開始
    #plt.plot(t,v,'-')
    #plt.plot(t,sgf,'-')
# ========================================
# 預防數據來源總量過少，以及區間過小或等於0 
# ===========================================
    if(len(t)>100):
        pass
    else:
        window_size_percent=1
    #print('wdSize:',window_size_percent)
    
#==================
#  記錄多項式係數、Delta,type 為list 
#==================
    # 由 0 為n次，1為n-1次，如此類推
    coef_ary=[]
    coeff_5=[]
    coeff_4=[]
    coeff_3=[]
    coeff_2=[]
    coeff_1=[]
    coeff_0=[]
    Dlta=[]
#==================
#  記錄解壓縮、RMSE 數據,type 為list 
#==================
    
    decompData=[]
    RMSECpData=[]
    
    #first_inl=0
    R2_PnR=[]
    R2_ngt=[]
    R2_noon=[]
    R2_afr=[]
    R2_mrg=[]

#==================
#  記錄不同時段的數據,type 為list 
#==================
    mrgData=[]
    noonData=[]
    aftData=[]
    ngtData=[]
    
    
#==================
#  設定其他預設值 
#==================
    interval=int(len(t)*window_size_percent)-1
    start=[]
    end=[]
    #intervalAry=[]
    rsqRW=0
    ys_line=[]
    tot_segNum=0
    dirSnr=''
    DeltaCnt=0
    dtMean={}
    sensor=fileName[6:-1]
#==================
#  儲存分段最佳值 
#==================
    Maxr2RW=0.0
    MaxEnd=0
    MaxCoeff=[]
    MaxYs=[]
    
    
# =========================
# 判斷數據集是溫度或濕度
# =========================
    if(sensor=='T'):
        #print(fileName)
        dirSnr=sensor+'\\'
    elif(sensor=='H'):
        #print(fileName)
        dirSnr=sensor+'\\'
    #dirFoder='Data_csv\\SlidingWindow\\Chebyshev\\'+dirSnr+'\\Poly_Index '+str(pIndex)+'\\max angle '+str(angle) #'\\Poly_Index '+str(pIndex)+'\\SGwd_length '+str(SGwd_length)
    #dirFoder='Data_csv\\SlidingWindow\\Chebyshev\\'+dirSnr
    
#========================
# 運算結果檔案位置
#========================
    dirFoder='Data_csv\\SlidingWindow\\Chebyshev\\'+dirSnr
    fT.mkfolder(dirFoder)
    dirWCdetailFolder='Data_csv\\SlidingWindow\\Chebyshev\\Mean\\Worst compressing data\\'
    fT.mkfolder(dirWCdetailFolder)
#================================
# condition
#================================
    c=1
    segNum=0
    mrg=0
    noon=0
    aft=0
    ngt=0
    OmrgData=[]
    OnonData=[]
    OaftData=[]
    OngtData=[]
    OrgMrg=0
    OrgNoon=0
    OrgAft=0
    OrgNgt=0
    mxErr=0
    while(startPt<(len(t))):
        #MaxStart=0
        islimit=False
# =============================================================================
#             
#             if ((interval-startPt)<=min_time_interval) :
#                 interval=interval+int((len(t)/10))
#     
# =============================================================================
        for i in range(interval,startPt-1,-1):
            if ((i-startPt)>1):
                endPt=i+1
                delta=endPt-startPt
                coeff,ys_line,rsqRW,MAE,Cv = mthT.polyLine(startPt,endPt,polyIndex,t,data)
                if(rsqRW>=Maxr2RW):
                     Maxr2RW=rsqRW
                     MaxYs=ys_line
                     MaxEnd=endPt
                     MaxCoeff=coeff
            elif((i-startPt)<=1):                 
                if(Maxr2RW==0.0):
                    endPt=len(t)
                    delta=endPt-startPt
                    coeff,ys_line,rsqRW,MAE,Cv  = mthT.polyLine(startPt,endPt,polyIndex,t,data)
                    islimit=True
                else:
                    rsqRW=Maxr2RW
                    endPt=MaxEnd
                    coeff=MaxCoeff
                    delta=endPt-startPt
                    ys_line=MaxYs
                    delta=endPt-startPt
                    islimit=True
            if (rsqRW>=min_r2 or Cv<=maxCv) or islimit :    
                start.append(times[startPt])                
                if(endPt!=len(times)):    
                    end.append(times[endPt])
                else:
                    end.append(times[endPt-1])  
                #print(coeff)
# =============================================================================
#                 coeff_5.append(float(coeff[5]))
#                 coeff_4.append(float(coeff[4]))
#                 coeff_3.append(float(coeff[3]))
#                 coeff_2.append(float(coeff[2]))
#                 coeff_1.append(float(coeff[1]))
#                 coeff_0.append(float(coeff[0]))
# =============================================================================
          
                coef_ary.extend(coeff)
                decompData.extend(ys_line)
                Dlta.append(delta)
                DeltaCnt =DeltaCnt+endPt-startPt
                
                
# =============================================================================
#                 global pltData
# =============================================================================
        
                c+=1
                R2_PnR.append(round(rsqRW,3))
                startPt= endPt
                interval=startPt+int((len(t)*window_size_percent))-1
                
# =============================================================================
                if (interval>=len(t)):
                    interval=len(t)-1
                MaxCoeff=[]
                MaxYs=[]
                Maxr2RW=0.0
                MaxEnd=interval
                break
            
    tEnd = time.time()#計時結束
    
    
#========================
# 計算不同時間段的結果
#========================
    
    #print(len(start))
    for x in range(len(start)):
        if(start[x].hour<6):
            mrg+=1
        elif(start[x].hour>=6 and start[x].hour<12):    
            noon+=1
        elif(start[x].hour>=12 and start[x].hour<=18):    
            aft+=1
        elif(start[x].hour>=18 and start[x].hour<=23):  
            ngt+=1
            
            
    for x in range(len(data)):
        if(times[x].hour<6):
            OrgMrg+=1
            OmrgData.append(data[x])
            mrgData.append(decompData[x])
        elif(times[x].hour>=6 and times[x].hour<12):
            OrgNoon+=1
            OnonData.append(data[x])
            noonData.append(decompData[x])
        elif(times[x].hour>=12 and times[x].hour<=18):
            OrgAft+=1
            OaftData.append(data[x])
            aftData.append(decompData[x])
        elif(times[x].hour>=18 and times[x].hour<=23):
            OrgNgt+=1
            OngtData.append(data[x])
            ngtData.append(decompData[x])
# =======================
# 輸出每天壓縮後的詳細資料
# =======================
            
            
# =============================================================================
#     #==each day polynorimal data
#     dtPolyData={'start':start,
#                 'end':end,
#                 'min_r2':min_r2,
#                 'poly n':pIndex,
#                 'wd_size%':wdsize_percent,
#                 'min_interval%':min_interval_percent,
#                 'angle':angle,
#                 'coef':coef_ary,
#                 'Delta':Dlta,
#                 'coeff_5':coeff_0,
#                 'coeff_4':coeff_1,
#                 'coeff_3':coeff_2,
#                 'coeff_2':coeff_3,
#                 'coeff_1':coeff_4,
#                 'coeff_0':coeff_5,
#                 'R2_Raw':R2_PnR
#                 }
#     headerPD=['start','end','Delta','coeff_5','coeff_4','coeff_3','coeff_2','coeff_1','coeff_0']
#     #輸出沒有了r2 值
#     dfpolyDtlData=pd.DataFrame(dtPolyData,columns=headerPD,index=None)
#     SnrCsvfileName='Data_csv\\SlidingWindow\\Chebyshev\\Mean\\Worst compressing data'+'\\'+fileName+"_DetailData.csv"
#     dfpolyDtlData.to_csv(SnrCsvfileName,mode='w',index=None)
# =============================================================================
     
# =================================
# 計算四個時間段的R2、RMSE、壓縮比
# =================================            
            
# =============================================================================
#     print(OmrgData)
#     print(mrgData)
# =============================================================================
    cpMrg=compR(mrg,OrgMrg),
    cpNoon=compR(noon,OrgNoon),
    cpAft=compR(aft,OrgAft),
    cpNgt=compR(ngt,OrgNgt),
    rmseMrg=calRMSE(OmrgData,mrgData),
    rmseNoon=calRMSE(OnonData,noonData),
    rmseAft=calRMSE(OaftData,aftData),
    rmseNgt=calRMSE(OngtData,ngtData)

# =============================================================================
#     print('總結:times=',len(times),'deCompDaya=',len(decompData),'min_r2=',min_r2)
#     print('分段數量: 0-6點: ',mrg,'  6-12點: ',noon,'  12-18點: ',aft,'  18-23點: ',ngt)
#     print('R2: 0-6點: ',round(mthT.coeff_of_determination(np.array(OmrgData),mrgData,startPt,endPt),3),
#           '6-12點: ',round(mthT.coeff_of_determination(np.array(OnonData),noonData,startPt,endPt),3),
#           '12-18點: ',round(mthT.coeff_of_determination(np.array(OaftData),aftData,startPt,endPt),3),
#           '18-23點: ',round(mthT.coeff_of_determination(np.array(OngtData),ngtData,startPt,endPt),3))
#     print('壓縮比: 0-6點: ',compR(mrg,OrgMrg),
#           '6-12點: ',compR(noon,OrgNoon),
#           '12-18點: ',compR(aft,OrgAft),
#           '18-23點: ',compR(ngt,OrgNgt))
#     print('RMSE: 0-6點: ',calRMSE(OmrgData,mrgData),
#           '6-12點: ',calRMSE(OnonData,noonData),
#           '12-18點: ',calRMSE(OaftData,aftData),
#           '18-23點: ',calRMSE(OngtData,ngtData))
# =============================================================================
    
    
    #print('wd Length: ',SGwd_length,'SGpolyOrd: ',SGpolyOrd)
    hdr_timeData=["date",
                  "Sensor",
                  "segMrg",
                  "segNoon",
                  "segAft",
                  "segNgt",
                  "cpMrg",
                  "cpNoon",
                  "cpAft",
                  "cpNgt",
                  "rmseMrg",
                  "rmseNoon",
                  "rmseAft",
                  "rmseNgt"
                ]
    
    timeData={"date":fileName[:-3],
              "Sensor":fileName[6:],
                  "segMrg":mrg,
                  "segNoon":noon,
                  "segAft":aft,
                  "segNgt":ngt,
                  "cpMrg":cpMrg,
                  "cpNoon":cpNoon,
                  "cpAft":cpAft,
                  "cpNgt":cpNgt,
                  "rmseMrg":rmseMrg,
                  "rmseNoon":rmseNoon,
                  "rmseAft":rmseAft,
                  "rmseNgt":rmseNgt
         }


    df4periods=pd.DataFrame(timeData,columns=hdr_timeData)
    print(ssrType,end='')
    MeanCsvfileName=dirMean+'\\2020_4periods_Data\\R2t'+str(min_r2)+'_wd'+str(window_size_percent)+'_pIndex'+str(polyIndex)+'_'+dt.datetime.now().strftime('%m%d')+'.csv'
    if (pth.isfile(MeanCsvfileName)!=True):  
        df4periods.to_csv(MeanCsvfileName,mode='w',index=None)
    else:
        df4periods.to_csv(MeanCsvfileName,mode='a',header=None,index=None)
    df4periods=pd.DataFrame(None,None)
# ============================      
#   輸出解壓縮的詳細數據
# ============================       
# =============================================================================
#     #==each day deCompression data
#     dtdeCData={'times':times,
#             'Data':decompData
#             }
#     
#     headerDC=['times','Data']
#     dfdeCData=pd.DataFrame(dtdeCData,columns=headerDC,index=None)
#     
#     deCompCsvfileName='Data_csv\\SlidingWindow\\Chebyshev\\Mean\\Worst compressing data'+'\\'+fileName+"_DecompressionDetailData.csv"
# 
#     dfdeCData.to_csv(deCompCsvfileName,mode='w',index=None)
#     
# =============================================================================
    
    segNum=len(start)# num of segement 
    tot_segNum+=segNum# total number of segements in all data 
    Nr=len(t)*2*8
    Nc=segNum*(pIndex+4)*8
    comp_ratio=(Nr-Nc)/Nr
    #print(dfpolyDtlData)
    #R2_RnS=round(mthT.coeff_of_determination(np.array(data),sgf,startPt,endPt),3)
    R2=round(mthT.coeff_of_determination(np.array(data),decompData,startPt,endPt),3)
    MeanR2=round(np.mean(R2),3)
    STD=round(np.std(R2_PnR,ddof=1 ),3)
    
    
    
    
#==============================================================================
# this for loop is compare Compress data and Raw data
# =============================================================================    
    temp=[]
    for n in range(len(data)):
        temp.append(np.abs(np.round(data[n],5)-np.round(decompData[n],5)))
    maxErr=np.max(temp)
    minErr=np.min(temp)
            
    MeanRMSE=calRMSE(data,decompData)
    CRatio=round(comp_ratio,3)
    timer=tEnd-tStart
    #print('分段總數= ',str(mrg+noon+aft+ngt),'  Mean R2=',MeanR2,'  CompRatio=',CRatio,'  RMSE=',MeanRMSE,)
    #print('MeanR2PnR = ' ,MeanR2PnR,', MeanR2PnS = ',MeanR2PnS)
    #print(fileName[:2]+fileName[3:])
    dtMean={
            'date':fileName[:-3],
            'Sensor':fileName[6:],
            'data Length':len(t),
            'pIndex':pIndex,
            'wdsize':window_size_percent,
            'Cv':maxCv,
            'segNum':segNum,
            'comp_ratio':CRatio,
            'timer': timer,
            'Mean_R2':MeanR2,
            'R2_STD':STD,
            'Mean_RMSE':MeanRMSE,
            'Max_Err':maxErr,
            'Min_Err':minErr
            }
#    print(df)
#    df.head()
    #print(pltData)
    #print(sensor,end=',')  
    
    global pltData
    #print(len(plotTime),':',len(decompData))
    pltData += [
                    go.Scatter(
                        x=plotTime, # assign x as the dataframe column 'x'
                        y=decompData,
                        mode='lines',
                        name='CHEB',
                        marker=dict(
                                size=5,
                                color='rgba(255,0,0,0.9)'
                                )
                        )]
# ============================      
#  畫圖 to html 
# ============================
    if isplot:
        #print(window_size_percent)
        pltT.PlotLy(t,window_size_percent,min_r2,polyIndex,maxCv,fileName,pltData,plotisOpen)
    return dtMean

#^^^^^^^^^^^^^^^^^^  Sub Functions ^^^^^^^^^^^^^^^









if __name__ =="__main__":
    fileName=fT.getFileName(mypath)
    #print(fileName)
    dirMean='Data_csv\\CHEB(CoD)_Result'
    fT.mkfolder(dirMean)
    
    
# ============================      
#  條件選擇 
# ============================
    pIndex=4##多項式次數
    min_r2=0.95##Rt2閥值
    wdsize_percent=0.1#
    maxCv=0.025##離散係數
    
    #min_interval_percent=0.00#limit  = 0.001
# ============================  

    Isplot=True
    plotisOpen=True
    #rndFile=88#random.randint(0,len(fileName))
    count=1
    if(count==0): 
        count+=1
    reset=True
    spr2Mean=[]
    spRatio=[]
    spTimer=[]
    OldSplist=[]
    spNum=30
    fileCount=0
    Tfile=0
    Hfile=0
    #test=list(range(80,86))
    testList35=[2,15,19,28,29,30,31,32,39,48,49,51,70,73,86,88,
              95,97,98,102,110,117,118]
    testList67=[6,21,26,27,30,35,40,41,48,52,53,55,56,60,61,69,
                70,73,78,84,85,91,98,101,104,110,117,118,137,147]

    test=[30,29]
    while(count<=1):

        print('time:',count,end=' ')
        print('pIndex: ',pIndex,' wd Size: ',wdsize_percent,'r2: ',min_r2 ,'Cv: ',maxCv)
        #print('Interval: ',str(round(min_interval_percent,5)))
    # =============================================================================
    #     dirMean='Data_csv\\SlidingWindow\\Chebyshev\\Mean\\SGpolyOrd '\
    #     +str(Ord)+'\\PolyIndex '+str(pIndex)+'\\SGwd_length '+str(w)\
    #     +'\\max angle '+str(angle)
    # =============================================================================
        
        ltMean=[]
# =============================================================================
#         spList=rSp.Sampling(len(fileName),spNum,OldSplist)##簡單隨機抽樣樣品 list
#         OldSplist.append(spList)
# ============================================================================= 
        ## 讀取RawData Ver 的1000個樣本
        ## 改動了readOldList 中的檔案名稱
        #spList=rdList.readOldList(count-1)
        
        #print(len(fileName))
#=========== for start ===============
        for i in test:#range(len(fileName))  全部數據
            global pltData
            sensorType=fileName[i][6:]  
            if(sensorType=='T1' or sensorType=='T2' or sensorType=='H1' or sensorType=='H2' ):
                data,times=fT.readCSV(mypath,fileName[i])
                plotTime=[]
                for ts in times:
                    plotTime.append(datetime.datetime.fromtimestamp(time.mktime(ts.timetuple())))
                t=[]
                v=[]
                pltData=[]
                tot_segNum=0
                stop=False        
                ssrType=sensorType[0]
                for x in range(len(data)):
                    t.append(x)
                    v.append(float(data[x]))

                #print(times)
                pltData += [
                    go.Scatter(
                        x=plotTime, # assign x as the dataframe column 'x'
                        y=v,
                        name='Raw',
                        mode='lines',
                        marker=dict(
                                size=10,
                                color='rgba(104,103,103,0.8)'),
                                line=dict(width=5,))]
                ltMean.append(CHEB_cod(fileName[i],
                                       min_r2=min_r2,
                                       polyIndex=pIndex,
                                       window_size_percent=wdsize_percent,
                                       maxCv=maxCv,
                                       isplot=Isplot))
#======== for end =================
        fileCount=i    
        
           
# ============================      
#   輸出壓縮結果 to Excel
# ============================

        #print('wd Length: ',SGwd_length,'SGpolyOrd: ',SGpolyOrd)
        Meanheader=['date',
                    'Sensor',
                    'data Length',
                    'pIndex',
                    'wdsize',
                    'Cv',
                    'segNum',
                    'comp_ratio',
                    'Mean_R2',
                    'R2_STD',
                    'Mean_RMSE',
                    'Max_Err',
                    'Min_Err',
                    'timer'
                    ]

        #   重置dfpolyMeanDay內的DataFrame 格式及資料
        dfpolyMeanDay=pd.DataFrame(None,None)
        dfpolyMeanDay=pd.DataFrame(ltMean,columns=Meanheader)
        MeanCsvfileName=dirMean+'\\2020_Test_Data\\R2t'+str(min_r2)+'_wd'+str(wdsize_percent)+'_pIndex'+str(pIndex)+'_'+dt.datetime.now().strftime('%m%d')+'.csv'
        if (pth.isfile(MeanCsvfileName)!=True):  
            dfpolyMeanDay.to_csv(MeanCsvfileName,mode='w',index=None)
        else:
            dfpolyMeanDay.to_csv(MeanCsvfileName,mode='a',header=None,index=None)
    # =============================================================================
    #         if (pth.isfile(MeanCsvfileName)!=True):  
    #            dfpolyMeanDay.to_csv(MeanCsvfileName,mode='w',index=None)
    #         else:
    #             dfpolyMeanDay.to_csv(MeanCsvfileName,mode='a',header=None,index=None)
    # =============================================================================
        #print(dfpolyMeanDay)
    # =============================================================================
    #     print('sgf window length: ',SGwd_length)
    #     print('sgf SGpolyOrder: ',SGpolyOrd)
    #     print('反應差: ',max_slope)
    #     print('最小決定系數: ',min_r2)
    #     print('擬合n次多項式 n : ',polyIndex)
    #    
    #     print('All R2 R&S mean :',round(np.mean(dfpolyMeanDay['R2_R&S']),3),
    #           'All R2 P&S mean :',round(np.mean( dfpolyMeanDay['Mean_R2_P&S']),3))
    #     
    # =============================================================================
        mean_segTol=round(np.mean(dfpolyMeanDay['segNum']),3)
        T_r2=[]
        H_r2=[]
        T_err=[]
        H_err=[]
        T_RMSE=[]
        H_RMSE=[]
        T_Comp=[]
        H_Comp=[]
        cntT=0
        cntH=0
        #print(dfpolyMeanDay)
        for i in range(0,len(dfpolyMeanDay)):
            checkChar=dfpolyMeanDay['Sensor'][i][0]
            if (checkChar=='T'):
                #print (i,dfpolyMeanDay.iloc[i]['Mean_R2'])
                T_Comp.append(dfpolyMeanDay.iloc[i]['comp_ratio'])
                T_r2.append(dfpolyMeanDay.iloc[i]['Mean_R2'])
                T_err.append(dfpolyMeanDay.iloc[i]['Max_Err'])
                T_RMSE.append(dfpolyMeanDay.iloc[i]['Mean_RMSE'])
                cntT+=1
            if (checkChar=='H'):
                cntH+=1
                #print (i,dfpolyMeanDay.iloc[i]['Mean_R2'])
                H_Comp.append(dfpolyMeanDay.iloc[i]['comp_ratio'])
                H_r2.append(dfpolyMeanDay.iloc[i]['Mean_R2'])
                H_err.append(dfpolyMeanDay.iloc[i]['Max_Err'])
                H_RMSE.append(dfpolyMeanDay.iloc[i]['Mean_RMSE'])
# ============================      
#   計算所有結果的平均值
# ============================
        #idx=dfpolyMeanDay.index[dfpolyMeanDay['Sensor']=='T'].tolist()
        #T_meanindf=dfpolyMeanDay['Mean_R2'].values
        #H_meanindf=dfpolyMeanDay.loc['Mean_R2_P&S',[dfpolyMeanDay['Sensor']=='T'].tolist
        
        mean_totLen=round(np.mean(dfpolyMeanDay['data Length']),3)
        mean_compRatio=round(np.mean(dfpolyMeanDay['comp_ratio']),3)
        allR2pnr=round(np.mean(dfpolyMeanDay['Mean_R2']),3)
        Tr2pnr=round(np.mean(T_r2),3)
        Hr2pnr=round(np.mean(H_r2),3)
        R2STDall=round(np.std(dfpolyMeanDay['Mean_R2']),3)
        allRMSE=round(np.mean(dfpolyMeanDay['Mean_RMSE']),3)
        RMSESTDall=round(np.std(dfpolyMeanDay['Mean_RMSE']),3)
        mMax=round(np.mean(dfpolyMeanDay['Max_Err']),3)
        mTcomp=round(np.mean(T_Comp),3)
        mHcomp=round(np.mean(H_Comp),3)
        mTrmse=round(np.mean(T_RMSE),3)
        mHrmse=round(np.mean(H_RMSE),3)
        mTerr=round(np.mean(T_err),3)
        mHerr=round(np.mean(H_err),3)
        TimerMean=round(np.mean(dfpolyMeanDay['timer']),3)
        


 
# ============================      
#   輸出所有平均值結果 to Excel
# ============================       
        tolMean={'count':count,
                 'Poly n':[pIndex],
                 'wd_size_%':wdsize_percent,
                 'min_r2':min_r2,
                 'Cv':maxCv,
                 'mean_segTol':[mean_segTol],
                 'mean_totalLength':[mean_totLen],
                 'mean_compRatio':[mean_compRatio],
                 'T CompR':mTcomp,
                 'H CompR':mHcomp,
                 'All R2 P&R mean':allR2pnr,
                 'R2_STD_All':R2STDall,
                 'T R2 mean':Tr2pnr,
                 'totNumT':cntT,
                 'H R2 mean':Hr2pnr,
                 'totNumH':cntH,
                 'Mean_RMSE':allRMSE,
                 'T_RMSE':mTrmse,
                 'H_RMSE':mHrmse,
                 'Mean_max':mMax,
                 'T_Max_err':mTerr,
                 'H_Max_err':mHerr,
                 'RMSE_STD_All':RMSESTDall,
                 'TimerMean':TimerMean
                 }
        tolMheader=['count',
                    'Poly n',
                    'wd_size_%',
                    'min_r2',
                    'Cv',
                    'mean_segTol',
                    'mean_totalLength',
                    'mean_compRatio',
                    'T CompR',
                    'H CompR',
                    'All R2 P&R mean',
                    'R2_STD_All',
                    'T R2 mean',
                    'H R2 mean',
                    'Mean_RMSE', 
                    'T_RMSE',
                    'H_RMSE',
                    'T_Max_err',
                    'H_Max_err',
                    'Mean_max',
                    'RMSE_STD_All',
                    'TimerMean'
                    ]
        #tolMeanFile='Data_csv\\SlidingWindow\\Chebyshev\\Mean\\AllMeanRaw&Poly.csv'
        tolMeanFile='Data_csv\\CHEB(CoD)_Result\\2020_Mean_Result\\'+dt.datetime.now().strftime('%m%d')+'.csv'
        dftolMean=pd.DataFrame(None,None)
        dftolMean=pd.DataFrame(tolMean,columns=tolMheader)
        #print(dftolMean)
        #print(tolMeanFile)
        if (pth.isfile(tolMeanFile)!=True):  
            dftolMean.to_csv(tolMeanFile,mode='w',index=None)
        else:
            dftolMean.to_csv(tolMeanFile,mode='a',header=None,index=None)
       #   重置dfpolyMeanDay內的DataFrame 格式及資料

        #dftolMean.to_csv(tolMeanFile,mode='a',line_terminator="\n")
        #print('count : '+str(count))
        #print('==== R2 : '+str(min_r2)+' pIndex :'+str(pIndex)+' wds : '+str(wdsize_percent)+'====')
        #print('File Count = ', fileCount, ',# T file =',cntT,',# H file',cntH)

#========================================
#  自動累加區間、閥值或次數
#========================================
        if(min_r2==1.0):
            pIndex-=1
            pIndex=np.round(pIndex)
            min_r2=0.05
        if(pIndex<1):
            wdsize_percent+=0.25
            wdsize_percent=np.round(wdsize_percent,2)
            pIndex=10
            min_r2=0.05
        
        min_r2+=0.05
        min_r2=np.round(min_r2,2)
        count+=1
        print("")
# =============================================================================
#     AllSpList={'list':OldSplist}
#     dfAllSpList=pd.DataFrame(AllSpList)
#     AllSpListPath='Data_csv\\SlidingWindow\\Chebyshev\\Mean\\Less100AllSplistRAWT.csv'
#     if (pth.isfile(AllSpListPath)!=True):  
#         dfAllSpList.to_csv(AllSpListPath,mode='w',header=None,index=None)
#     else:
#         dfAllSpList.to_csv(AllSpListPath,mode='a',header=None,index=None)
# =============================================================================

