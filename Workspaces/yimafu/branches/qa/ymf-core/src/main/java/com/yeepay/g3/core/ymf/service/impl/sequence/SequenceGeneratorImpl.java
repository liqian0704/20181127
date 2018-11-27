package com.yeepay.g3.core.ymf.service.impl.sequence;
import com.yeepay.g3.core.ymf.dao.sequence.SequenceDao;
import com.yeepay.g3.core.ymf.service.sequence.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *二维码ID生成
 */
@Service
public class SequenceGeneratorImpl implements SequenceGenerator {

	@Autowired
	private SequenceDao sequenceDao;
	@Override
	public String generateSequence(int width) {
		Long sequence = sequenceDao.getSequenceValueByName();
		int tempWidth = (int) Math.pow(10, width);
		
		Long s = sequence % tempWidth;
		String temp = String.valueOf(s);
		if(temp.length() < width){
			for (int i = temp.length(); i < width; i++) {
				temp = "0" + temp; 
			}
		}else{
			temp = temp.substring(width - temp.length(), width);
		}
		String tempStr = "";
		for(int i =0; i<width; i++){
			tempStr += "0";
		}
		if(tempStr.equals(temp)){
			sequence = (Long) sequenceDao.getSequenceValueByName();
			sequence = 1L;
			s = sequence % tempWidth;
			temp = String.valueOf(s);
			for (int i = temp.length(); i < width; i++) {
				temp = "0" + temp; 
			}
		}
		return temp;
	}

	@Override
	public String generateSequence() {
		return generateSequence(6);
	}
}
