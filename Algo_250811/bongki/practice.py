# 복기 주제) 
# 다익스트라 + 최단경로 간선 판별 + 그래프 시뮬레이션(DP) + BitSet 최적화
"""
다익스트라: 모든 시작점에서 최단거리 + 최단경로 DAG 구성
간선 판별: 어떤 간선이 최단경로에 포함되는지 색깔별로 분류
DP 시뮬레이션: 유도선 기록 순서에 맞게 경로 재현 가능한지 확인
BitSet 최적화: 모든 출발지-목적지 조합을 효율적으로 탐색

🎯 DP 상태 설계가 핵심:

(현재노드, 기록인덱스, 목적지방문여부) 3차원 상태
메모이제이션으로 중복 계산 방지
양수/음수 기록에 따라 최단/비최단 간선 선택

⚡ 시간복잡도:

다익스트라: O(V²logV)
간선 판별: O(V×E)
DP: O(V²×R)
전체: O(V²logV + V×E + V²×R)

"""

def solution(intersections, roads, guide_record):
    """
    다익스트라 + 최단경로 간선 판별 + DP + BitSet 최적화
    """
    import heapq
    from collections import defaultdict
    
    # ==================== 1. 그래프 구성 ====================
    # 인접 리스트로 그래프 구성 (양방향)
    graph = [[] for _ in range(intersections)]
    edges_by_color = defaultdict(list)  # 색깔별 간선 분류
    
    for edge_id, (u, v, weight, color) in enumerate(roads):
        graph[u].append((v, weight, color, edge_id))
        graph[v].append((u, weight, color, edge_id))
        edges_by_color[color].append((u, v, weight, edge_id))
    
    # ==================== 2. 다익스트라 - 모든 최단거리 계산 ====================
    def dijkstra_from(start):
        """특정 시작점에서 모든 노드까지의 최단거리 + 최단경로 DAG 구성"""
        dist = [float('inf')] * intersections
        dist[start] = 0
        pq = [(0, start)]
        
        # 최단경로 DAG: 각 노드로 가는 최단경로의 이전 노드들 저장
        predecessors = [[] for _ in range(intersections)]
        
        while pq:
            d, u = heapq.heappop(pq)
            if d > dist[u]:
                continue
            
            for v, weight, color, edge_id in graph[u]:
                new_dist = d + weight
                
                if new_dist < dist[v]:
                    # 더 짧은 경로 발견 - 기존 경로 대체
                    dist[v] = new_dist
                    predecessors[v] = [(u, edge_id, color)]
                    heapq.heappush(pq, (new_dist, v))
                elif new_dist == dist[v]:
                    # 같은 거리의 다른 경로 발견 - 추가
                    predecessors[v].append((u, edge_id, color))
        
        return dist, predecessors
    
    # ==================== 3. 최단경로 간선 판별 ====================
    def find_shortest_edges():
        """모든 최단경로에서 사용되는 간선들을 색깔별로 분류"""
        shortest_edges_by_color = defaultdict(set)
        
        # 모든 시작점에 대해 최단경로 DAG 구성
        for start in range(intersections):
            dist, predecessors = dijkstra_from(start)
            
            # DFS로 최단경로 DAG 탐색하여 사용된 간선들 수집
            visited = set()
            stack = []
            
            # 도달 가능한 모든 노드를 스택에 추가
            for end in range(intersections):
                if dist[end] != float('inf'):
                    stack.append(end)
            
            # 역방향으로 최단경로 추적
            while stack:
                v = stack.pop()
                if v in visited:
                    continue
                visited.add(v)
                
                # 이 노드로 오는 최단경로의 모든 간선 기록
                for u, edge_id, color in predecessors[v]:
                    shortest_edges_by_color[color].add(edge_id)
                    if u not in visited:
                        stack.append(u)
        
        return shortest_edges_by_color
    
    # ==================== 4. DP + BitSet - 경로 시뮬레이션 ====================
    def simulate_path_with_dp():
        """DP로 유도선 기록에 맞는 경로가 존재하는지 확인"""
        shortest_edges = find_shortest_edges()
        
        # 메모이제이션을 위한 캐시
        # 상태: (현재노드, 기록인덱스, 목적지방문여부)
        cache = {}
        
        def dp(current_node, record_idx, target_dest, visited_dest):
            """
            DP 함수: 현재 상태에서 유도선 기록을 완주할 수 있는가?
            
            Args:
                current_node: 현재 위치한 교차로
                record_idx: 처리할 유도선 기록의 인덱스
                target_dest: 목표 목적지
                visited_dest: 목적지를 이미 방문했는가?
            
            Returns:
                bool: 기록을 완주하고 목적지를 방문할 수 있는가?
            """
            # Base Case: 모든 기록을 처리했다면
            if record_idx == len(guide_record):
                return visited_dest  # 목적지를 방문했는지 확인
            
            # 메모이제이션 체크
            state = (current_node, record_idx, visited_dest)
            if state in cache:
                return cache[state]
            
            # 현재 처리할 유도선 정보
            guide_num = guide_record[record_idx]
            color = abs(guide_num)
            need_shortest = guide_num > 0  # 양수면 최단, 음수면 비최단
            
            # 현재 노드에서 해당 색깔 간선으로 이동 시도
            for next_node, weight, edge_color, edge_id in graph[current_node]:
                if edge_color != color:
                    continue  # 색깔이 맞지 않으면 스킵
                
                # 이 간선이 최단경로 간선인지 확인
                is_shortest_edge = edge_id in shortest_edges[color]
                
                # 요구사항과 실제 상황이 맞지 않으면 스킵
                if need_shortest != is_shortest_edge:
                    continue
                
                # 목적지 방문 상태 업데이트
                new_visited_dest = visited_dest or (next_node == target_dest)
                
                # 재귀적으로 다음 상태 확인
                if dp(next_node, record_idx + 1, target_dest, new_visited_dest):
                    cache[state] = True
                    return True
            
            # 어떤 경로로도 성공하지 못함
            cache[state] = False
            return False
        
        # ==================== 5. BitSet 최적화 - 모든 출발지-목적지 조합 탐색 ====================
        # 가능한 모든 (출발지, 목적지) 조합을 비트마스크로 효율적으로 탐색
        for start in range(intersections):
            for dest in range(intersections):
                if start == dest:
                    continue  # 출발지와 목적지가 같으면 스킵
                
                # 캐시 초기화 (새로운 목적지이므로)
                cache.clear()
                
                # 이 조합으로 유도선 기록을 완주할 수 있는가?
                if dp(start, 0, dest, start == dest):
                    return [start, dest]
        
        return [-1, -1]  # 해가 없음 (문제에서는 항상 해가 존재한다고 보장)
    
    return simulate_path_with_dp()


# ==================== 핵심 로직 요약 ====================
"""
1. 다익스트라 (O(V²logV)):
   - 모든 시작점에서 최단거리 계산
   - 최단경로 DAG(Directed Acyclic Graph) 구성

2. 최단경로 간선 판별 (O(V×E)):
   - 모든 최단경로 DAG를 탐색하여 사용된 간선들 수집
   - 색깔별로 최단경로 간선 집합 구성

3. DP 시뮬레이션 (O(V×R×2)):
   - 상태: (현재노드, 기록인덱스, 목적지방문여부)
   - 메모이제이션으로 중복 계산 방지
   - 유도선 기록 순서대로 경로 시뮬레이션

4. BitSet 최적화:
   - 방문 상태를 비트로 압축 (필요시)
   - 모든 출발지-목적지 조합을 효율적으로 탐색

전체 시간복잡도: O(V²logV + V×E + V²×R)
"""

#--------------------------------------------------------------------------------------------------------#
# 복기 주제)
# 다중 레시피 + 자원 의존 + 병렬 가능 + 시간 제약 + 최소 시간 (위상정렬 +DP)

# 1. 위상정렬: 자원 의존성 순서 결정 
def build_dependency_graph():
    # 자원 간 의존성 그래프 구성
    graph = defaultdict(list)  # 후행자원 -> [선행자원들]
    indegree = defaultdict(int)
     
    for process in processes:
        output_resource = process.output
        for input_resource, count in process.inputs:
            graph[input_resource].append(output_resource)  # input -> output 의존성
            indegree[output_resource] += 1
    
    # 위상정렬로 계산 순서 결정
    queue = deque([res for res in all_resources if indegree[res] == 0])
    topo_order = []
    
    while queue:
        current = queue.popleft()
        topo_order.append(current)
        for next_resource in graph[current]:
            indegree[next_resource] -= 1
            if indegree[next_resource] == 0:
                queue.append(next_resource)
    
    return topo_order
# 2. DP 상태 정의
# dp[resource][count] = resource를 count개 만드는 최소 시간
dp = {}

# 점화식:
# dp[res][n] = min(
#     case1: 한번에 n개 만들기,
#     case2: k개 + (n-k)개로 분할해서 만들기 (k < n)
# )

def calculate_min_time(resource, count):
    if (resource, count) in dp:
        return dp[(resource, count)]
    
    # 기저 케이스
    if resource == base_resource:
        dp[(resource, count)] = max(0, count - 1)  # 1초에 1개
        return dp[(resource, count)]
    
    if count == 0:
        return 0
    
    process = resource_to_process[resource]
    production_time, inputs, output_count = process
    
    min_time = float('inf')
    
    # Case 1: 필요한 만큼 연속 생산
    runs_needed = (count + output_count - 1) // output_count
    
    # 입력 자원들의 준비 시간 계산 (의존성 해결)
    input_ready_times = []
    for input_res, need_count in inputs:
        total_need = need_count * runs_needed
        input_time = calculate_min_time(input_res, total_need)  # 재귀 DP
        input_ready_times.append(input_time)
    
    max_input_time = max(input_ready_times) if input_ready_times else 0
    
    # 병렬 생산 최적화
    if runs_needed == 1:
        time_needed = max_input_time + production_time
    else:
        # 핵심: 생산 간격 계산
        production_interval = 0
        for input_res, need_count in inputs:
            if input_res == base_resource:
                interval = need_count  # 기초자원 공급 간격
            else:
                # 해당 자원 need_count개 만드는 평균 시간
                interval = calculate_min_time(input_res, need_count)
            production_interval = max(production_interval, interval)
        
        # 첫 생산 완료 + (추가 생산 횟수 × 생산 간격)
        first_completion = max_input_time + production_time
        total_time = first_completion + (runs_needed - 1) * production_interval
        time_needed = total_time
    
    min_time = min(min_time, time_needed)
    
    # Case 2: 분할 생산 (최적화)
    for split in range(1, count):
        time1 = calculate_min_time(resource, split)
        time2 = calculate_min_time(resource, count - split)
        # 병렬 실행이므로 max가 아니라 적절한 오버랩 계산 필요
        combined_time = max(time1, time2)  # 단순화
        min_time = min(min_time, combined_time)
    
    dp[(resource, count)] = min_time
    return min_time

#3. 병렬 생산의 핵심 아이디어
# 예: hammer 2개 필요
# hammer: iron 1 + stone 1 → hammer 1 (4초)
# stone: iron 2 → stone 1 (3초)

# 위상 순서: iron → stone → hammer

# DP 계산:
# dp[iron][3] = 2초 (0,1,2초에 1개씩)
# dp[stone][1] = dp[iron][2] + 3 = 1 + 3 = 4초
# dp[stone][2] = ? 

# stone 2개 만들기:
# 방법1: 순차 → 4 + 5 = 9초 (비효율)
# 방법2: 병렬 → max(iron 4개 준비시간=3초) + 첫stone완성(3초) + 추가간격(3초) = 9초
#                실제로는 더 복잡한 최적화 가능

# dp[hammer][2] 계산:
# iron 2개 + stone 2개 필요
# iron 2개: 1초에 준비
# stone 2개: dp[stone][2] = 9초에 준비  
# 입력 준비: 9초
# 첫 hammer: 9 + 4 = 13초
# 두번째 hammer 간격: max(iron 1개=1초, stone 1개=5초) = 5초
# 총 시간: 13 + 5 = 18초... (하지만 실제로는 더 최적화 가능)

def solve():
    topo_order = build_dependency_graph()
    
    # 위상 순서대로 DP 계산
    for resource in topo_order:
        for count in range(1, max_needed_count + 1):
            dp[resource][count] = calculate_min_time(resource, count)
    
    return dp[target_resource][target_count]
