package personal.service;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import personal.beans.Paper;
import personal.beans.Question;
import personal.dao.IPaperDao;
import personal.dao.IQuestionDao;

public class PaperServiceImpl implements IPaperService {

	Logger logger = LogManager.getLogger(this.getClass());

	/*
	 * 防止出现内存泄漏的情况，下面几个值不要过大
	 * 但值太小进化效果不明显
	 */	
	// 种群大小
	private static final Integer SIZE = 5;
	private static final Integer KIDS_SIZE = 5;
	// 进化代数
	private static final Integer CYCLE = 5;

	private IPaperDao paperDao;

	private IQuestionDao questionDao;

	public IPaperDao getPaperDao() {
		return paperDao;
	}

	public void setPaperDao(IPaperDao paperDao) {
		this.paperDao = paperDao;
	}

	public IQuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(IQuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public void exportPaperById(Integer id, String docPath) {

		Paper paper = paperDao.selectPaperById(id);

		OutputStream os = null;
		BufferedOutputStream bos = null;
		XWPFDocument document = null;

		try {
			os = new FileOutputStream(docPath + "paper_" + id + ".docx");
			bos = new BufferedOutputStream(os, 1024);
			document = new XWPFDocument();

			/****************** 标题 *********************/
			// 创建段落
			XWPFParagraph titleParagraph = document.createParagraph();
			// 居中
			titleParagraph.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun titleRun = titleParagraph.createRun();
			titleRun.setText("试卷号：" + id);
			// 加粗
			titleRun.setBold(true);
			// 换行
			titleRun.addCarriageReturn();

			/***************** 选择题 ******************/
			XWPFParagraph choiceParagraph = document.createParagraph();
			XWPFRun choiceRun = choiceParagraph.createRun();
			choiceRun.setText("一、选择题");
			choiceRun.addCarriageReturn();

			String choiceStr = paper.getChoice();
			String[] choices = choiceStr.split(",");
			Question cQuestion;
			for (int i = 0; i < choices.length; i++) {
				cQuestion = questionDao.selectQuestionById(choices[i]);
/*
				 输出至txt文件，换行符问题
				 String temp = cQuestion.getDetails().replaceAll("\n","\r\n");
*/
				// 输出至docx文件，换行符问题
				String[] temp = cQuestion.getDetails().split("\n");
				for (int line = 0; line < temp.length; line++) {
					choiceRun.setText(temp[line]);
					choiceRun.addCarriageReturn();
				}
				temp = null;
				choiceRun.addCarriageReturn();
			}
			
			/***************** 填空题 ******************/
			XWPFParagraph blankParagraph = document.createParagraph();
			XWPFRun blankRun = blankParagraph.createRun();
			blankRun.setText("二、填空题");
			blankRun.addCarriageReturn();

			String blankStr = paper.getBlank();
			String[] blanks = blankStr.split(",");
			Question bQuestion;
			for (int i = 0; i < blanks.length; i++) {
				bQuestion = questionDao.selectQuestionById(blanks[i]);
				String[] temp = bQuestion.getDetails().split("\n");
				for (int line = 0; line < temp.length; line++) {
					blankRun.setText(temp[line]);
					blankRun.addCarriageReturn();
				}
				temp = null;
				blankRun.addCarriageReturn();
			}
			/***************** 判断题 ******************/
			XWPFParagraph judgeParagraph = document.createParagraph();
			XWPFRun judgeRun = judgeParagraph.createRun();
			judgeRun.setText("三、判断题");
			judgeRun.addCarriageReturn();

			String judgeStr = paper.getJudge();
			String[] judges = judgeStr.split(",");
			Question jQuestion;
			for (int i = 0; i < judges.length; i++) {
				jQuestion = questionDao.selectQuestionById(judges[i]);
				String[] temp = jQuestion.getDetails().split("\n");
				for (int line = 0; line < temp.length; line++) {
					judgeRun.setText(temp[line]);
					judgeRun.addCarriageReturn();
				}
				temp = null;
				judgeRun.addCarriageReturn();
			}
			/***************** 编程题 ******************/
			XWPFParagraph programParagraph = document.createParagraph();
			XWPFRun programRun = programParagraph.createRun();
			programRun.setText("四、编程题");
			programRun.addCarriageReturn();

			String programStr = paper.getProgram();
			String[] programes = programStr.split(",");
			Question pQuestion;
			for (int i = 0; i < programes.length; i++) {
				pQuestion = questionDao.selectQuestionById(programes[i]);
				String[] temp = pQuestion.getDetails().split("\n");
				for (int line = 0; line < temp.length; line++) {
					programRun.setText(temp[line]);
					programRun.addCarriageReturn();
				}
				temp = null;
				programRun.addCarriageReturn();
			}
			// 写入
			document.write(bos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null)
					bos.close();
				if (os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Paper createPaper(Set<String> epoint, Double ep, Double cs, Integer ca, Double bs, Integer ba, Double js,
			Integer ja, Double ps, Integer pa) {
		List<Question> cmaterial = questionDao.selectAllChoiceQuestions();
		List<Question> bmaterial = questionDao.selectAllBlankQuestions();
		List<Question> jmaterial = questionDao.selectAllJudgeQuestions();
		List<Question> pmaterial = questionDao.selectAllProgramQuestions();

		List<Paper> population = new ArrayList<Paper>();
		// 历史最优个体
		Paper bestPaper = new Paper();
		Paper temp = null;

		logger.info("**************组卷开始*************");
		/************************** 选择题部分 *****************************/
		Paper bestPaper1 = new Paper();
		bestPaper1.setGrade(0.0);
		// 初始化选择题部分
		population = init("choice", cmaterial, population, cs, ca, epoint, ep);
		// 初始化历史最优
		// logger.info("[选择题]种群初始化完成,size:"+population.size());
		try {
			for (int i = 0; i < SIZE; i++) {
				temp = population.get(i);
				// logger.info("[temp_fit:"+temp.getGrade()+"]、[best_fit:"+bestPaper1.getGrade()+"]");
				if (temp.getGrade() > bestPaper1.getGrade()) {
					// logger.info("交换");
					bestPaper1 = (Paper) temp.clone();
				}
			}
//			logger.info("[初始化后最优]" + bestPaper1.getGrade());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		logger.info("[选择题]开始进化");
//		double max = 0.0;
		// 进化50代
		for (int i = 0; i < CYCLE; i++) {
			// 交叉
			population = cross("choice", population, cs, ca, epoint, ep);
			// 变异
			population = variation("choice", cmaterial, population, cs, ca, epoint, ep);
			// 适者生存，选择优秀个体得到下一代种群
			population = select(population);
			// 更新历史最优个体
			try {
//				max = 0.0;
				for (int j = 0; j < SIZE; j++) {
					temp = population.get(j);
					// logger.info("[第" + i + "代][temp:" + temp.getGrade() +
					// "]、[best:" + bestPaper1.getGrade() + "]");
					if (temp.getGrade() > bestPaper1.getGrade()) {
//						 logger.info("[第"+i+"代][temp_fit:"+temp.getGrade()+"]、[best_fit:"+bestPaper1.getGrade()+"]");
						// logger.info("****交换***");
						bestPaper1 = (Paper) temp.clone();
					}
//					if(temp.getGrade() > max)
//						max = temp.getGrade();
				}
//				System.out.println("["+i+"]BEST_FIT = " + max);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
//			 logger.info("[选择题]进化到第"+i+"代历史最优个体适应度  = " + bestPaper1.getGrade());
//			System.out.println(" ||  BEST_in_history_FIT  = " + bestPaper1.getGrade());
		}
		// logger.info("[最优适应度"+bestPaper1.getGrade()+"]");
		logger.info("[选择题]进化完成");
		/********************* 填空题部分 *********************/
		Paper bestPaper2 = new Paper();
		bestPaper2.setGrade(0.0);
		population.clear();
		population = init("blank", bmaterial, population, bs, ba, epoint, ep);
		// logger.info("[填空题]种群初始化完成"+population.size());
		try {
			for (int i = 0; i < SIZE; i++) {
				temp = population.get(i);
				if (temp.getGrade() > bestPaper2.getGrade()) {
					bestPaper2 = (Paper) temp.clone();
				}
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		logger.info("[填空题]开始进化");
		for (int i = 0; i < CYCLE; i++) {
			population = cross("blank", population, bs, ba, epoint, ep);
			population = variation("blank", bmaterial, population, bs, ba, epoint, ep);
			population = select(population);
			try {
				for (int j = 0; j < SIZE; j++) {
					temp = population.get(j);
					if (temp.getGrade() > bestPaper2.getGrade()) {
						bestPaper2 = (Paper) temp.clone();
					}
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
//			logger.info("[填空题]第"+i+"代历史最优个体适应度  = " + bestPaper2.getGrade());
		}
		logger.info("[填空题]进化完成");
		/******************** 判断题部分 **********************/

		Paper bestPaper3 = new Paper();
		bestPaper3.setGrade(0.0);
		population.clear();
		population = init("judge", jmaterial, population, js, ja, epoint, ep);
		// logger.info("[判断题]种群初始化完成"+population.size());
		try {
			for (int i = 0; i < SIZE; i++) {
				temp = population.get(i);
				if (temp.getGrade() > bestPaper3.getGrade()) {
					bestPaper3 = (Paper) temp.clone();
				}
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		logger.info("[判断题]开始进化");
		for (int i = 0; i < CYCLE; i++) {
			population = cross("judge", population, js, ja, epoint, ep);
			population = variation("judge", jmaterial, population, js, ja, epoint, ep);
			population = select(population);
			try {
				for (int j = 0; j < SIZE; j++) {
					temp = population.get(j);
					if (temp.getGrade() > bestPaper3.getGrade()) {
						bestPaper3 = (Paper) temp.clone();
					}
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
//			logger.info("[判断题]第"+i+"代历史最优个体适应度  = " + bestPaper3.getGrade());
		}
		logger.info("[判断题]进化完成");
		/***************** 编程题部分 *****************/
		Paper bestPaper4 = new Paper();
		bestPaper4.setGrade(0.0);
		population.clear();
		population = init("program", pmaterial, population, ps, pa, epoint, ep);
		// logger.info("[编程题]种群初始化完成"+population.size());
		try {
			for (int i = 0; i < SIZE; i++) {
				temp = population.get(i);
				if (temp.getGrade() > bestPaper4.getGrade()) {
					bestPaper4 = (Paper) temp.clone();
				}
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		logger.info("[编程题]进化开始");
		for (int i = 0; i < CYCLE; i++) {
			population = cross("program", population, ps, pa, epoint, ep);
			population = variation("program", pmaterial, population, ps, pa, epoint, ep);
			population = select(population);
			try {
				for (int j = 0; j < SIZE; j++) {
					temp = population.get(j);
					if (temp.getGrade() > bestPaper4.getGrade()) {
						bestPaper4 = (Paper) temp.clone();
					}
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
//			logger.info("[编程题]第"+i+"代历史最优个体适应度  = " + bestPaper4.getGrade());
		}
		logger.info("[编程题]进化完成");

		bestPaper.setLimit(false);
//		bestPaper.setMdate(new Timestamp(System.currentTimeMillis()));
		bestPaper.setGrade(ep);
		bestPaper.setChoice(bestPaper1.getChoice());
		bestPaper.setBlank(bestPaper2.getBlank());
		bestPaper.setJudge(bestPaper3.getJudge());
		bestPaper.setProgram(bestPaper4.getProgram());
		bestPaper.setChoiceScore(cs);
		bestPaper.setBlankScore(bs);
		bestPaper.setJudgeScore(js);
		bestPaper.setProgramScore(ps);

		logger.info("最终试卷：" + bestPaper);
		return bestPaper;
	}

	/**
	 * 种群初始化
	 * 
	 * @param flag
	 *            确定题型
	 * @param population
	 *            需要被初始化的种群
	 * @param material
	 *            原材料
	 * @param score
	 *            题目分数
	 * @param amount
	 *            题目数量
	 * @param epoint
	 *            期望知识点
	 * @param ep
	 *            期望难度系数
	 * @return
	 */
	public List<Paper> init(String flag, List<Question> material, List<Paper> population, Double score, Integer amount,
			Set<String> epoint, Double ep) {
		Integer size = material.size();
		Random random = new Random();
		String topic = "";
		for (int i = 0; i < SIZE; i++) {
			// 生成amount个不重复随机整数，[0, size)
			Set<Integer> randoms = new HashSet<Integer>();
			while (randoms.size() < amount) {
				randoms.add(random.nextInt(size));
			}
			// logger.info("Set.size = " + randoms.size());
			// 将随机数序列整理成题号字符串形式，顺便计算知识点覆盖率和难度系数
			Question question = null;
			String[] temp = null;
			// 平均分 = sum(分值 * 题目难度系数)
			Double average = 0.0;
			Double temp1 = null;
			Set<String> point = new HashSet<String>();
			topic = "";
			for (Integer integer : randoms) {
				question = material.get(integer);
				topic = topic + " " + question.getId();
				// logger.info(topic);
				temp = question.getPoint().split(",");
				temp1 = Double.parseDouble(question.getGrade());
				for (int j = 0; j < temp.length; j++) {
					if (epoint.contains(temp[j])) {
						point.add(temp[j]);
					}
				}
				// 计算平均分；难度和难度系数的转换
				average += score * (6.0 - temp1) / 5.0;
			}
			// 格式化字符串
			topic = topic.trim().replaceAll(" ", ",");
			// logger.info("初始化字符串："+topic);
			// 知识点覆盖率 = 所有题目涵盖知识点 / 要求知识点
			Double rate = (double) point.size() / (double) epoint.size();
			// 难度系数 = 平均分 / 满分
			Double p = average / (score * amount);
			// 生成一个个体
			Paper paper = new Paper();
			// 计算该个体的适应度（越接近1越好），值暂且存放在paper的grade属性中
			Double fit = 1 - 0.5 * (1 - rate) - 0.5 * Math.abs(ep - p);
//			Double fit = 1 - Math.abs(ep - p);
			
//			 logger.info("[知识点覆盖率："+rate+"]、[难度系数："+p+"]、[适应度："+fit+"]");
			paper.setGrade(fit);
			switch (flag) {
			case "choice":
				paper.setChoice(topic);
				paper.setChoiceScore(score);
				break;
			case "blank":
				paper.setBlank(topic);
				paper.setBlankScore(score);
				break;
			case "judge":
				paper.setJudge(topic);
				paper.setJudgeScore(score);
				break;
			default:
				paper.setProgram(topic);
				paper.setProgramScore(score);
				break;
			}

			// 新个体添加到种群
			population.add(paper);
		}
		// logger.info("初始化种群size:"+population.size());
		return population;
	}

	/**
	 * 交叉
	 * 
	 * @param flag
	 *            确定题型
	 * @param population
	 *            需要被初始化的种群
	 * @param score
	 *            题目分数
	 * @param amount
	 *            题目数量
	 * @param epoint
	 *            期望知识点
	 * @param ep
	 *            期望难度系数
	 * @return
	 */
	public List<Paper> cross(String flag, List<Paper> population, Double score, Integer amount, Set<String> epoint,
			Double ep) {
		Random random = new Random();
		Integer f, m;
		Paper father, mother;
		Boolean isOk;
		// 存放交叉产生的新个体
		List<Paper> kids = new ArrayList<Paper>();
		// 产生新个体
		for (int i = 0; i < KIDS_SIZE; i++) {
			// 选择双亲
			f = random.nextInt(SIZE);
			m = random.nextInt(SIZE);
			while (f == m) {
				m = random.nextInt(SIZE);
			}
			father = population.get(f);
			mother = population.get(m);
			String[] fstr = {};
			String[] mstr = {};
			switch (flag) {
			case "choice":
				fstr = father.getChoice().split(",");
				mstr = mother.getChoice().split(",");
				break;
			case "blank":
				fstr = father.getBlank().split(",");
				mstr = mother.getBlank().split(",");
				break;
			case "judge":
				fstr = father.getJudge().split(",");
				mstr = mother.getJudge().split(",");
				break;
			default:
				fstr = father.getProgram().split(",");
				mstr = mother.getProgram().split(",");
				break;
			}

			// 单点交叉，随机选取交叉点
			Integer crossPoint = random.nextInt(amount - 1);
			// 交叉得到的新个体不可能含重复试题
			isOk = true;
			for (int j = 0; j <= crossPoint; j++) {
				for (int j2 = crossPoint + 1; j2 < amount; j2++) {
					if (mstr[j].equals(fstr[j2]) || fstr[j].equals(mstr[j2])) {
						isOk = false;
						break;
					}
				}
				if (!isOk) {
					break;
				}
			}
			// 可以交叉
			if (isOk) {
				String kidStr = "";
				Double average = 0.0;
				Set<String> point = new HashSet<String>();
				Question question = null;
				String[] temp = null;
				// 进行交叉;子代的基因序列，取父亲的前半段，母亲的后半段
				for (int j = 0; j < amount; j++) {
					if (j <= crossPoint) {
						kidStr = kidStr + " " + fstr[j];
						question = questionDao.selectQuestionById(fstr[j]);
						temp = question.getPoint().split(",");
						for (int k = 0; k < temp.length; k++) {
							if (epoint.contains(temp[k])) {
								point.add(temp[k]);
							}
						}
						average += score * Double.parseDouble(question.getGrade());
					} else {
						kidStr = kidStr + " " + mstr[j];
						question = questionDao.selectQuestionById(mstr[j]);
						temp = question.getPoint().split(",");
						for (int k = 0; k < temp.length; k++) {
							if (epoint.contains(temp[k])) {
								point.add(temp[k]);
							}
						}
						average += score * (6.0 - Double.parseDouble(question.getGrade())) / 5.0;
					}
				}
				kidStr = kidStr.trim().replaceAll(" ", ",");
				// logger.info("交叉："+kidStr);
				Paper kid = father;
				// 知识点覆盖率
				Double rate = (double) point.size() / (double) epoint.size();
				// 难度系数
				Double p = average / (score * amount);
				// 适应度
				Double fit = 1 - 0.5 * (1 - rate) - 0.5 * Math.abs(ep - p);
//				Double fit = 1 - Math.abs(ep - p);
				kid.setGrade(fit);

				switch (flag) {
				case "choice":
					kid.setChoice(kidStr);
					break;
				case "blank":
					kid.setBlank(kidStr);
					break;
				case "judge":
					kid.setJudge(kidStr);
					break;
				default:
					kid.setProgram(kidStr);
					break;
				}
				kids.add(kid);
			} else {
				i--;
			}
		}
		// 合并
		population.addAll(kids);
		// logger.info("繁衍后种群size："+population.size());
		return population;
	}

	/**
	 * 变异
	 * 
	 * @param flag
	 *            确定题型
	 * @param material
	 *            原材料
	 * @param population
	 *            需要被初始化的种群
	 * @param score
	 *            题目分数
	 * @param amount
	 *            题目数量
	 * @param epoint
	 *            期望知识点
	 * @param ep
	 *            期望难度系数
	 * @return
	 */
	public List<Paper> variation(String flag, List<Question> material, List<Paper> population, Double score,
			Integer amount, Set<String> epoint, Double ep) {
		// System.out.println("variation:"+population.size());
		// 变异概率
		Double probability = 0.09;
		Random random = new Random();
		Paper paper = null;
		Question question = null;
		String[] genes = null;
		for (int i = 0; i < SIZE + KIDS_SIZE; i++) {
			// 变异
			if (random.nextDouble() < probability) {
				paper = population.get(i);
				switch (flag) {
				case "choice":
					genes = paper.getChoice().split(",");
					break;
				case "blank":
					genes = paper.getBlank().split(",");
					break;
				case "judge":
					genes = paper.getJudge().split(",");
					break;
				default:
					genes = paper.getProgram().split(",");
					break;
				}

				// 随机选取一个基因变异
				Integer index = random.nextInt(amount);
				question = material.get(random.nextInt(material.size()));
				// 保证产生的试卷合格
				while (question.getId().equals(genes[index])) {
					question = material.get(random.nextInt(material.size()));
				}
				genes[index] = question.getId();

				// 计算知识点覆盖率,难度系数
				Set<String> point = new HashSet<String>();
				String[] temp = null;
				String str = "";
				Double average = 0.0;
				for (int j = 0; j < genes.length; j++) {
					// 当前题目的知识点
					question = questionDao.selectQuestionById(genes[j]);
					temp = question.getPoint().split(",");
					for (int j2 = 0; j2 < temp.length; j2++) {
						if (epoint.contains(temp[j2])) {
							point.add(temp[j2]);
						}
					}
					str = str + " " + genes[j];
					average = +score * (6.0 - Double.parseDouble(question.getGrade())) / 5.0;
				}
				// logger.info("genes.length = " + genes.length);
				str = str.trim().replaceAll(" ", ",");
				// logger.info("变异："+str);
				// 适应度
				Double rate = (double) point.size() / (double) epoint.size();
				Double p = average / (score * amount);
				Double fit = 1 - 0.5 * (1 - rate) - 0.5 * Math.abs(ep - p);
//				Double fit = 1 - Math.abs(ep - p);
				paper.setGrade(fit);
				switch (flag) {
				case "choice":
					paper.setChoice(str);
					break;
				case "blank":
					paper.setBlank(str);
					break;
				case "judge":
					paper.setJudge(str);
					break;
				default:
					paper.setProgram(str);
					break;
				}

				// 更新种群集合
				population.set(i, paper);
			}
		}
		// logger.info("变异后种群size："+population.size());
		return population;
	}

	/**
	 * 选择（轮盘赌）
	 * 
	 * @param flag
	 *            确定题型
	 * @param population
	 *            需要被初始化的种群
	 * @return
	 */
	public List<Paper> select(List<Paper> population) {
		// 种群所有个体适应度之和
		Double total = 0.0;
		for (int i = 0; i < SIZE + KIDS_SIZE; i++) {
			total += population.get(i).getGrade();
		}

		// 每个个体被选中的概率
		double[] chance = new double[SIZE + KIDS_SIZE];
		for (int i = 0; i < chance.length; i++) {
			chance[i] = population.get(i).getGrade() / total;
		}

		// 制作轮盘赌的刻度
		double[] measure = new double[SIZE + KIDS_SIZE];
		Double temp = 0.0;
		for (int i = 0; i < measure.length; i++) {
			temp += chance[i];
			measure[i] = temp;
		}

		// 开始选择,共选择SIZE个个体
		List<Paper> children = new ArrayList<Paper>();
		Random random = new Random();
		Double needle;
		Set<Integer> count = new HashSet<Integer>();
		// logger.info(count.size());
		for (int i = 0; i < SIZE;) {
			needle = random.nextDouble();
			// logger.info(i+":"+needle);
			for (int j = 0; j < measure.length; j++) {
				if (needle > measure[j]) {
					continue;
				} else if (count.contains(j)) {
					// 指针转到同一区域，重新转
					break;
				} else {
					// 选择到一个个体
					children.add(population.get(j));
					count.add(j);
					i++;
					break;
				}
			}
		}
		// logger.info("选择完成后种群size："+children.size());
		return children;
	}

	@Override
	public void addPaper(Paper paper) {
		paperDao.insertPaper(paper);
	}

	@Override
	public Integer getNewPaperId() {
		return paperDao.selectNewPaperId();
	}

	@Override
	public List<Paper> getPapersInfo(Integer start, Integer limit, Integer flag) {
		return paperDao.selectAllPapersInfo(start, limit, flag);
	}

	@Override
	public Integer getCount(Integer flag) {
		return paperDao.selectCount(flag);
	}

	@Override
	public List<Paper> getPapersInfo2(Integer start, Integer limit) {
		return paperDao.selectAllPapersInfo2(start, limit);
	}

	@Override
	public Integer getCount2() {
		return paperDao.selectCount2();
	}


	@Override
	public List<Question> getPaperChoice(Integer paperId) {
		Paper paper = paperDao.selectPaperById(paperId);
		String[] choiceStr = paper.getChoice().split(",");
		List<Question> choices = new ArrayList<Question>();
		Question choice = null;
		for(int i = 0; i < choiceStr.length; i++){
			choice = questionDao.selectQuestionById(choiceStr[i]);
			choices.add(choice);
		}
		return choices;
	}

	@Override
	public List<Question> getPaperBlank(Integer paperId) {
		Paper paper = paperDao.selectPaperById(paperId);
		String[] blankStr = paper.getBlank().split(",");
		List<Question> blanks = new ArrayList<Question>();
		Question blank = null;
		for(int i = 0; i < blankStr.length; i++){
			blank = questionDao.selectQuestionById(blankStr[i]);
			blanks.add(blank);
		}
		return blanks;
	}

	@Override
	public List<Question> getPaperJudge(Integer paperId) {
		Paper paper = paperDao.selectPaperById(paperId);
		String[] judgeStr = paper.getJudge().split(",");
		List<Question> judges = new ArrayList<Question>();
		Question judge = null;
		for(int i = 0; i < judgeStr.length; i++){
			judge = questionDao.selectQuestionById(judgeStr[i]);
			judges.add(judge);
		}
		return judges;
	}

	@Override
	public List<Question> getPaperProgram(Integer paperId) {
		Paper paper = paperDao.selectPaperById(paperId);
		String[] programStr = paper.getProgram().split(",");
		List<Question> programes = new ArrayList<Question>();
		Question program = null;
		for(int i = 0; i < programStr.length; i++){
			program = questionDao.selectQuestionById(programStr[i]);
			programes.add(program);
		}
		return programes;
	}

	@Override
	public Paper getPaperById(Integer id) {
		return paperDao.selectPaperById(id);
	}

	@Override
	public void changeLimit(Integer id) {
		paperDao.updateLimit(id);
	}
}
