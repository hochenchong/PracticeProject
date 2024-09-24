/**
 * 计算 MBTI 性格结果
 *
 * @param answers
 */
export const calculateMBTI = (answers: string[]): string => {
  const counts = { E: 0, I: 0, T: 0, F: 0, N: 0, S: 0, J: 0, P: 0 };

  answers.forEach(answer => {
    if (answer) {
      counts[answer] += 1;
    }
  });

  const type =
    (counts.E > counts.I ? 'E' : 'I') +
    (counts.N > counts.S ? 'N' : 'S') +
    (counts.T > counts.F ? 'T' : 'F') +
    (counts.J > counts.P ? 'J' : 'P');

  return type;
};
