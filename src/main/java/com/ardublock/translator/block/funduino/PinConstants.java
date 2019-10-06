package com.ardublock.translator.block.funduino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import edu.mit.blocks.codeblocks.Block;

public class PinConstants extends TranslatorBlock
{

    public PinConstants(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
    {
        super(blockId, translator, codePrefix, codeSuffix, label);
        // translator.addHeaderFile("TinkerKit.h");
    }

    public String toCode()
        throws SocketNullException
    {
  	String ret;
	translator.addHeaderFile("Funduino.h");
        String gename=translator.getBlock(blockId).getGenusName();
        if (gename.startsWith("fu_listBut"))
          ret = "BUTT" + gename.substring(10,11);
        else if (gename.startsWith("fu_listLed"))
          ret = "LED" + gename.substring(10,11);
        else if (gename.startsWith("fu_listServo"))
          ret = "SERVO" + gename.substring(12,13);
        else if (gename.equals("fu_buzzer_pin"))
          ret = "BUZZER";
        else if (gename.equals("fu_trimmer_pin"))
          ret = "TRIMMER";
        else
          ret = gename;
        return (new StringBuilder()).append(codePrefix).append("FUNDUI_").append(ret).append(codeSuffix).toString();
    }
}
