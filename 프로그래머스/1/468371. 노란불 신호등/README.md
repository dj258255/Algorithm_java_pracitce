# [level 1] 노란불 신호등 - 468371 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/468371) 

### 성능 요약

메모리: 76 MB, 시간: 78.63 ms

### 구분

코딩테스트 연습 > 2025 카카오 하반기 1차

### 채점결과

정확성: 100.0<br/>합계: 100.0 / 100.0

### 제출 일자

2026년 03월 19일 21:57:33

### 문제 설명

<p>어떤 도로에 차량 신호등이 <code>n</code>개 있습니다. 모든 신호등은 항상 초록불 → 노란불 → 빨간불 순서로 반복되며, 각 신호의 지속 시간은 신호등마다 다릅니다. 시간은 1초부터 시작하며, 각 신호등은 처음에는 초록불 상태로 시작합니다.</p>

<p>이 도로에서는 가끔 정전이 일어나는데, 모든 신호등이 모두 노란불이 되면 정전이 발생한다는 사실이 밝혀졌습니다.</p>

<p>예를 들어 신호등이 2개이고, 각 신호등의 주기가 다음과 같다고 가정해 보겠습니다.</p>
<table class="table">
        <thead><tr>
<th>신호등</th>
<th>초록불</th>
<th>노란불</th>
<th>빨간불</th>
</tr>
</thead>
        <tbody><tr>
<td>1번</td>
<td>2초</td>
<td>1초</td>
<td>2초</td>
</tr>
<tr>
<td>2번</td>
<td>5초</td>
<td>1초</td>
<td>1초</td>
</tr>
</tbody>
      </table>
<p><img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/production/presigned_urls/69bc0131-0c4f-4609-9c47-4b649c40f860/%E1%84%89%E1%85%B5%E1%86%AB%E1%84%92%E1%85%A9%E1%84%83%E1%85%B3%E1%86%BC-1.drawio.png" title="" alt="신호등-1.drawio.png"></p>

<p>위 그림과 같이 13초에 처음으로 두 신호등이 모두 노란불이 됩니다. </p>

<p>신호등 <code>n</code>개의 신호 주기를 담은 2차원 정수 배열 <code>signals</code>가 매개변수로 주어집니다. 모든 신호등이 노란불이 되는 가장 빠른 시각(초)을 return 하도록 solution 함수를 완성해 주세요. 만약 모든 신호등이 노란불이 되는 경우가 존재하지 않는다면 -1을 return 해주세요.</p>

<hr>

<h5>제한사항</h5>

<ul>
<li>2 ≤ <code>signals</code>의 길이 = <code>n</code> ≤ 5

<ul>
<li><code>signals</code>의 원소는 <code>[G, Y, R]</code> 형태의 길이가 3인 정수 배열입니다. 순서대로 초록불, 노란불, 빨간불의 지속 시간을 의미합니다.</li>
<li>1 ≤ <code>G</code>, <code>Y</code>, <code>R</code> ≤ 18</li>
<li>3 ≤ <code>G + Y + R</code> ≤ 20</li>
</ul></li>
</ul>

<hr>

<h5>테스트 케이스 구성 안내</h5>

<p>아래는 테스트 케이스 구성을 나타냅니다. 각 그룹은 하나 이상의 하위 그룹으로 이루어져 있으며, 하위 그룹의 모든 테스트 케이스를 통과하면 해당 그룹에 할당된 점수를 획득할 수 있습니다.</p>
<table class="table">
        <thead><tr>
<th>그룹</th>
<th>총점</th>
<th>추가 제한 사항</th>
</tr>
</thead>
        <tbody><tr>
<td>#1</td>
<td>30%</td>
<td>신호등이 모두 노란불이 되는 시각이 20 이하인 정답이 존재합니다.</td>
</tr>
<tr>
<td>#2</td>
<td>30%</td>
<td>신호등이 모두 노란불이 되는 경우가 존재합니다.</td>
</tr>
<tr>
<td>#3</td>
<td>40%</td>
<td>추가 제한 사항 없음</td>
</tr>
</tbody>
      </table>
<hr>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>signals</th>
<th>result</th>
</tr>
</thead>
        <tbody><tr>
<td>[[2, 1, 2], [5, 1, 1]]</td>
<td>13</td>
</tr>
<tr>
<td>[[2, 3, 2], [3, 1, 3], [2, 1, 1]]</td>
<td>11</td>
</tr>
<tr>
<td>[[3, 3, 3], [5, 4, 2], [2, 1, 2]]</td>
<td>193</td>
</tr>
<tr>
<td>[[1, 1, 4], [2, 1, 3], [3, 1, 2], [4, 1, 1]]</td>
<td>-1</td>
</tr>
</tbody>
      </table>
<hr>

<h5>입출력 예 설명</h5>

<p><strong>입출력 예 #1</strong></p>

<p>문제 설명의 예시와 같습니다.</p>

<p><strong>입출력 예 #2</strong></p>
<table class="table">
        <thead><tr>
<th>신호등</th>
<th>초록불</th>
<th>노란불</th>
<th>빨간불</th>
</tr>
</thead>
        <tbody><tr>
<td>1번</td>
<td>2초</td>
<td>3초</td>
<td>2초</td>
</tr>
<tr>
<td>2번</td>
<td>3초</td>
<td>1초</td>
<td>3초</td>
</tr>
<tr>
<td>3번</td>
<td>2초</td>
<td>1초</td>
<td>1초</td>
</tr>
</tbody>
      </table>
<p><img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/production/presigned_urls/3011656a-b47d-40ea-baf4-15413beb7d44/%E1%84%89%E1%85%B5%E1%86%AB%E1%84%92%E1%85%A9%E1%84%83%E1%85%B3%E1%86%BC-2.drawio.png" title="" alt="신호등-2.drawio.png"></p>

<p>11초에 3개의 신호등이 모두 노란불이 됩니다.</p>

<p><strong>입출력 예 #3</strong></p>
<table class="table">
        <thead><tr>
<th>신호등</th>
<th>초록불</th>
<th>노란불</th>
<th>빨간불</th>
</tr>
</thead>
        <tbody><tr>
<td>1번</td>
<td>3초</td>
<td>3초</td>
<td>3초</td>
</tr>
<tr>
<td>2번</td>
<td>5초</td>
<td>4초</td>
<td>2초</td>
</tr>
<tr>
<td>3번</td>
<td>2초</td>
<td>1초</td>
<td>2초</td>
</tr>
</tbody>
      </table>
<p>193초에 3개의 신호등이 모두 노란불이 됩니다.</p>

<p><strong>입출력 예 #4</strong></p>

<p>모든 신호등이 노란불이 되는 경우가 존재하지 않으므로 -1을 return 해야 합니다.</p>


> 출처: 프로그래머스 코딩 테스트 연습, https://school.programmers.co.kr/learn/challenges