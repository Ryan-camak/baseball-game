package com.example.demo.baseball.Service;

import com.example.demo.baseball.Model.PitchRequest;
import com.example.demo.baseball.Model.BatterProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PitchService {

    @Autowired
    private OpenAiService openAiService;
    public String getAttackStrategy(String batterName) {
        String specialty = switch (batterName) {
            case "Ohtani" -> "擅長揮擊外角變化球";
            case "Judge" -> "擅長擊打速球";
            case "Suzuki" -> "擅長選球";
            default -> "打擊全面";
        };
        String prompt = String.format(
                """
                    去搜尋針對打者%s的打擊特性，你是職業棒球的情蒐教練教練，請給出投球策略建議，請參考以下格式:
                    1.球種:滑球
                    2.投球區域:內角高
                    3.球速:150km/h
                    回答字數限制在20字內，回答皆用繁體中文回答
                """
                ,batterName);
        return openAiService.getBatterDescription(prompt);
    }


    public String processPitch(PitchRequest request) {
        String batterName = request.getBatter();
        String specialty = switch (batterName) {
            case "Ohtani" -> "擅長揮擊外角變化球";
            case "Judge" -> "擅長擊打速球";
            case "Suzuki" -> "擅長選球";
            default -> "打擊全面";
        };
        BatterProfile batter = new BatterProfile(batterName, specialty);

        // 只傳投球內容，不再附帶 instruction
        String prompt = String.format(
            """
                我們來玩一個棒球投打對決的遊戲，你是要根據你扮演打者%s，我會投出一顆%s的%s，%s，請妳依造他的打擊特性，模擬打擊結果，回答請參考以下兩種格式：
                整個回答限制在50字內，並且回答皆用繁體中文回答。
                ex1:第一種情況飛球出局
                (1)擊球初速:170km
                (2)球的落點:2壘上空高飛球
                (3)擊球仰角:27度
                (4)這是一隻內野高飛球，打者出局(要依造球的落點給出合理的結果，1.如果是打者出局，必須明確說出打者出局，2.若是打者上壘必須明確說出，打者上到哪一個壘包)
                ex2:第二種情況
                (1)擊球初速:0km
                (2)球的落點:無
                (3)擊球仰角:0度
                (4)打者揮棒落空，或者好球進壘(依造前面資訊，若是顆好球打者沒揮棒你就必須說出「打者沒揮棒，記好球一顆」，若是引誘球引誘打者追打壞球你就必須說出「打者揮棒落空，記好球一顆」)
            """,batter.getName(),request.getPitchZone(),request.getPitchType(), request.getPitchSpeed());

        return openAiService.getBatterDescription(prompt);
    }
}

/*
 * 我們來玩一個棒球投打對決的遊戲，你是要根據你扮演的打者大谷翔平，依造他的打擊特性，我會投出一顆內角低的變化球，球速慢，你只要回答以下幾點(1)
 * 擊球初速球的落點，(2)球的落點，(3)擊球仰角，(4)擊球結果
 * 我給你幾個例子
 * ex1:第一種情況飛球出局
 * (1)擊球初速:170km
 * (2)球的落點:2壘上空高飛球
 * (3)擊球仰角:27度
 * (4)這是一隻內野高飛球，打者出局(要依造球的落點給出合理的結果，1.如果是打者出局，必須明確說出打者出局，2.若是打者上壘必須明確說出，
 * 打者上到哪一個壘包)
 * 
 * ex2:
 * (1)擊球初速:0km
 * (2)球的落點:無
 * (3)擊球仰角:0度
 * (4)打者揮棒落空，或者好球進壘(依造前面資訊，若是顆好球你就必須說出好球進壘，若是壞球你就必須說出打者揮棒落空，或者不揮棒等情形)
 * 整個回答限制在70字內
 */