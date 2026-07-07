import { useState, useEffect } from 'react';
import axios from 'axios';

const API = 'http://localhost:8080/api';

function App() {
  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState('');
  const [error, setError] = useState('');
  const [statusFilter, setStatusFilter] = useState('');

  const fetchTasks = async () => {
    const params = statusFilter ? { status: statusFilter } : {};
    const res = await axios.get(`${API}/tasks`, { params });
    setTasks(res.data.content || res.data);
  };

  useEffect(() => { fetchTasks(); }, [statusFilter]);

  const createTask = async () => {
    setError('');
    try {
      await axios.post(`${API}/tasks`, { title, projectId: 1 });
      setTitle('');
      fetchTasks();
    } catch (err) {
      setError(err.response?.data?.errors?.[0]?.message || 'Error creating task');
    }
  };

  const updateStatus = async (id, status) => {
    await axios.patch(`${API}/tasks/${id}/status?status=${status}`);
    fetchTasks();
  };

  const deleteTask = async (id) => {
    await axios.delete(`${API}/tasks/${id}`);
    fetchTasks();
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
      <h1>Task Tracker</h1>

      <div>
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Task title"
        />
        <button onClick={createTask}>Add Task</button>
        {error && <p style={{ color: 'red' }}>{error}</p>}
      </div>

      <div style={{ margin: '10px 0' }}>
        Filter:
        <select value={statusFilter} onChange={(e) => setStatusFilter(e.target.value)}>
          <option value="">All</option>
          <option value="TODO">TODO</option>
          <option value="DOING">DOING</option>
          <option value="DONE">DONE</option>
        </select>
      </div>

      <ul>
        {tasks.map((t) => (
          <li key={t.id}>
            <b>{t.title}</b> — {t.status} — {t.priority}
            <select value={t.status} onChange={(e) => updateStatus(t.id, e.target.value)}>
              <option value="TODO">TODO</option>
              <option value="DOING">DOING</option>
              <option value="DONE">DONE</option>
            </select>
            <button onClick={() => deleteTask(t.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;