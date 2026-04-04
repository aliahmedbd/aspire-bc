/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import { useState, ReactNode } from 'react';
import { 
  Trophy, 
  Calendar, 
  MapPin, 
  Users, 
  CreditCard, 
  User, 
  Settings, 
  ChevronLeft, 
  Bell, 
  Plus, 
  Phone, 
  MessageSquare, 
  CheckCircle2, 
  Info, 
  Zap, 
  Star,
  ShieldCheck,
  Smartphone,
  AlertCircle,
  Send,
  CalendarDays,
  Clock,
  LayoutGrid
} from 'lucide-react';
import { motion, AnimatePresence } from 'motion/react';

// --- Design System Tokens ---
const colors = {
  primary: "#003461",
  primaryContainer: "#004b87",
  onPrimaryContainer: "#8abcff",
  secondary: "#54606b",
  tertiaryFixed: "#bef500",
  onTertiaryFixed: "#151f00",
  tertiaryFixedDim: "#a6d700",
  background: "#f8fafb",
  surface: "#f8fafb",
  surfaceContainerLow: "#f2f4f5",
  surfaceContainerLowest: "#ffffff",
  onSurface: "#191c1d",
  onSurfaceVariant: "#424750",
  outlineVariant: "#c2c6d1",
  error: "#ba1a1a",
  errorContainer: "#ffdad6",
};

// --- Components ---

const BottomNav = ({ activeTab, setActiveTab }: { activeTab: string, setActiveTab: (tab: string) => void }) => {
  const tabs = [
    { id: 'home', label: 'Home', icon: LayoutGrid },
    { id: 'members', label: 'Members', icon: Users },
    { id: 'payments', label: 'Payments', icon: CreditCard },
    { id: 'profile', label: 'Profile', icon: User },
    { id: 'admin', label: 'Admin', icon: ShieldCheck },
  ];

  return (
    <nav className="fixed bottom-0 left-0 right-0 bg-white/80 backdrop-blur-xl border-t border-slate-100 px-4 pb-6 pt-3 flex justify-around items-center z-50 rounded-t-3xl shadow-[0_-12px_32px_rgba(0,52,97,0.06)]">
      {tabs.map((tab) => {
        const Icon = tab.icon;
        const isActive = activeTab === tab.id;
        return (
          <button
            key={tab.id}
            onClick={() => setActiveTab(tab.id)}
            className={`flex flex-col items-center justify-center px-4 py-2 rounded-xl transition-all active:scale-90 ${
              isActive ? 'bg-[#d9ff00] text-[#003461]' : 'text-slate-500'
            }`}
          >
            <Icon size={20} fill={isActive ? "currentColor" : "none"} />
            <span className="text-[10px] font-bold uppercase tracking-wider mt-1">{tab.label}</span>
          </button>
        );
      })}
    </nav>
  );
};

const Header = ({ title, showBack = false, onBack = () => {}, rightElement = null }: { title: string, showBack?: boolean, onBack?: () => void, rightElement?: ReactNode }) => (
  <header className="fixed top-0 left-0 right-0 h-16 bg-white/80 backdrop-blur-xl flex items-center justify-between px-6 z-50 border-b border-slate-50">
    <div className="flex items-center gap-4">
      {showBack && (
        <button onClick={onBack} className="p-2 -ml-2 hover:bg-slate-100 rounded-full transition-colors">
          <ChevronLeft size={24} className="text-[#004B87]" />
        </button>
      )}
      <h1 className="text-lg font-black tracking-tight text-[#003461] uppercase font-headline">
        {title}
      </h1>
    </div>
    {rightElement}
  </header>
);

// --- Screens ---

const LoginScreen = ({ onLogin }: { onLogin: () => void }) => {
  const [selected, setSelected] = useState('weekday');

  return (
    <div className="min-h-screen bg-[#f8fafb] flex flex-col items-center justify-center p-6 relative overflow-hidden">
      {/* Kinetic Background */}
      <div className="absolute top-[-10%] right-[-5%] w-[60%] h-[60%] bg-[#004b87]/5 rounded-full blur-[120px]" />
      <div className="absolute bottom-[-10%] left-[-5%] w-[40%] h-[40%] bg-[#bef500]/10 rounded-full blur-[100px]" />

      <div className="w-full max-w-md relative z-10">
        <header className="text-center mb-12">
          <div className="inline-flex items-center justify-center w-20 h-20 bg-[#003461] rounded-2xl mb-6 shadow-xl">
            <Trophy size={40} className="text-[#bef500]" />
          </div>
          <h1 className="text-5xl font-black tracking-tight text-[#003461] mb-2 font-headline">
            ASPIRE <span className="text-[#3c5000] italic">BC</span>
          </h1>
          <p className="text-slate-500 font-bold tracking-widest uppercase text-[10px]">High-Performance Badminton Club</p>
        </header>

        <div className="bg-white rounded-[2.5rem] p-8 shadow-2xl shadow-blue-900/5">
          <div className="mb-10">
            <h2 className="text-2xl font-bold text-slate-900 tracking-tight mb-2 font-headline">Welcome to Aspire</h2>
            <p className="text-slate-500 text-sm">Join the court and master your game with our elite training ecosystem.</p>
          </div>

          <div className="space-y-4 mb-10">
            <p className="text-[10px] font-black text-[#003461] uppercase tracking-[0.2em] mb-3">Select Membership Type</p>
            
            <button 
              onClick={() => setSelected('weekday')}
              className={`w-full flex items-center justify-between p-4 rounded-2xl border-2 transition-all ${
                selected === 'weekday' ? 'border-[#bef500] bg-white' : 'border-transparent bg-slate-50'
              }`}
            >
              <div className="flex items-center gap-4">
                <div className={`w-10 h-10 rounded-full flex items-center justify-center ${selected === 'weekday' ? 'bg-blue-50' : 'bg-slate-200'}`}>
                  <Calendar size={20} className="text-[#003461]" />
                </div>
                <div className="text-left">
                  <span className="block font-bold text-slate-900">Weekday Member</span>
                  <span className="block text-[10px] text-slate-500 uppercase font-bold tracking-wider">Mon - Fri Access • 6AM - 10PM</span>
                </div>
              </div>
              <div className={`w-6 h-6 rounded-full border-2 flex items-center justify-center ${selected === 'weekday' ? 'border-[#003461] bg-[#003461]' : 'border-slate-300'}`}>
                {selected === 'weekday' && <CheckCircle2 size={14} className="text-white" />}
              </div>
            </button>

            <button 
              onClick={() => setSelected('weekend')}
              className={`w-full flex items-center justify-between p-4 rounded-2xl border-2 transition-all ${
                selected === 'weekend' ? 'border-[#bef500] bg-white' : 'border-transparent bg-slate-50'
              }`}
            >
              <div className="flex items-center gap-4">
                <div className={`w-10 h-10 rounded-full flex items-center justify-center ${selected === 'weekend' ? 'bg-blue-50' : 'bg-slate-200'}`}>
                  <CalendarDays size={20} className="text-[#003461]" />
                </div>
                <div className="text-left">
                  <span className="block font-bold text-slate-900">Weekend Member</span>
                  <span className="block text-[10px] text-slate-500 uppercase font-bold tracking-wider">Sat - Sun Access • All Day Priority</span>
                </div>
              </div>
              <div className={`w-6 h-6 rounded-full border-2 flex items-center justify-center ${selected === 'weekend' ? 'border-[#003461] bg-[#003461]' : 'border-slate-300'}`}>
                {selected === 'weekend' && <CheckCircle2 size={14} className="text-white" />}
              </div>
            </button>
          </div>

          <button 
            onClick={onLogin}
            className="w-full bg-[#bef500] hover:bg-[#a6d700] text-[#151f00] font-black py-5 rounded-2xl flex items-center justify-center gap-3 shadow-xl shadow-lime-500/20 transition-all active:scale-95"
          >
            Continue with Google
          </button>
        </div>
      </div>
    </div>
  );
};

const HomeScreen = ({ onAddSession }: { onAddSession: () => void }) => {
  return (
    <div className="pb-32 pt-20 px-6">
      <Header 
        title="Aspire Badminton" 
        rightElement={
          <div className="flex items-center gap-4">
            <Bell size={20} className="text-slate-400" />
            <div className="w-10 h-10 rounded-xl bg-slate-200 overflow-hidden">
              <img src="https://picsum.photos/seed/user/100/100" alt="Profile" className="w-full h-full object-cover" />
            </div>
          </div>
        }
      />
      
      <div className="mb-10">
        <h2 className="text-5xl font-black text-[#003461] leading-none tracking-tighter font-headline">Up Next</h2>
        <p className="text-slate-500 text-sm font-medium mt-2">Join the next available court</p>
      </div>

      <div className="space-y-6">
        {[
          { title: "Tuesday Evening Smash", type: "WEEKDAY SESSION", date: "Oct 24, 18:00 - 20:00", court: "Court 4", spots: 4, color: "bg-blue-50 text-blue-700" },
          { title: "Weekend Open Play", type: "WEEKEND SESSION", date: "Oct 28, 09:00 - 12:00", court: "Main Hall - Court 1 & 2", spots: 2, color: "bg-[#003461] text-white" }
        ].map((session, i) => (
          <div key={i} className="bg-white rounded-[2rem] p-6 shadow-xl shadow-blue-900/5 border border-slate-50">
            <div className="flex justify-between items-start mb-6">
              <span className={`px-3 py-1 rounded-full text-[10px] font-black tracking-widest uppercase ${session.color}`}>
                {session.type}
              </span>
              <div className="flex -space-x-2">
                {[1, 2, 3].map(n => (
                  <div key={n} className="w-8 h-8 rounded-full border-2 border-white bg-slate-200 overflow-hidden">
                    <img src={`https://picsum.photos/seed/p${n+i}/50/50`} alt="" />
                  </div>
                ))}
                <div className="w-8 h-8 rounded-full border-2 border-white bg-slate-100 flex items-center justify-center text-[10px] font-bold text-slate-500">+8</div>
              </div>
            </div>
            
            <h3 className="text-xl font-bold text-slate-900 mb-4 font-headline">{session.title}</h3>
            
            <div className="space-y-3 mb-8">
              <div className="flex items-center gap-3 text-slate-500">
                <Calendar size={16} className="text-[#003461]" />
                <span className="text-sm font-medium">{session.date}</span>
              </div>
              <div className="flex items-center gap-3 text-slate-500">
                <MapPin size={16} className="text-[#003461]" />
                <span className="text-sm font-medium">{session.court}</span>
              </div>
            </div>

            <div className="flex items-center justify-between pt-4 border-t border-slate-50">
              <div>
                <p className="text-[10px] font-black text-slate-400 tracking-widest uppercase">Availability</p>
                <p className="text-sm font-black text-[#003461]">{session.spots} SPOTS LEFT</p>
              </div>
              <button className="bg-[#bef500] text-[#151f00] px-6 py-3 rounded-xl font-black text-xs uppercase tracking-tight shadow-lg shadow-lime-500/10 active:scale-95 transition-all">
                Join Session
              </button>
            </div>
          </div>
        ))}
      </div>

      <button 
        onClick={onAddSession}
        className="fixed right-6 bottom-32 w-14 h-14 bg-[#003461] text-white rounded-2xl flex items-center justify-center shadow-2xl shadow-blue-900/20 active:scale-90 transition-all z-50"
      >
        <Plus size={28} />
      </button>
    </div>
  );
};

const CreateSessionScreen = ({ onBack }: { onBack: () => void }) => {
  const [category, setCategory] = useState('weekday');

  return (
    <div className="pt-20 pb-32 px-6 min-h-screen bg-[#f8fafb]">
      <Header title="Create Session" showBack onBack={onBack} />
      
      <div className="mb-10 pt-4">
        <span className="text-[10px] font-black tracking-[0.2em] text-slate-400 uppercase">Session Logistics</span>
        <h2 className="text-5xl font-black text-[#003461] mt-2 leading-none font-headline">NEW COURT<br/>EVENT</h2>
      </div>

      <form className="space-y-8">
        <div className="space-y-3">
          <label className="text-[10px] font-black uppercase tracking-widest text-slate-500">Session Category</label>
          <div className="grid grid-cols-2 p-1.5 bg-slate-100 rounded-2xl">
            <button 
              type="button"
              onClick={() => setCategory('weekday')}
              className={`py-3 px-4 rounded-xl font-bold text-sm transition-all ${
                category === 'weekday' ? 'bg-white text-[#003461] shadow-sm' : 'text-slate-500'
              }`}
            >
              Weekday Session
            </button>
            <button 
              type="button"
              onClick={() => setCategory('weekend')}
              className={`py-3 px-4 rounded-xl font-bold text-sm transition-all ${
                category === 'weekend' ? 'bg-white text-[#003461] shadow-sm' : 'text-slate-500'
              }`}
            >
              Weekend Session
            </button>
          </div>
        </div>

        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-3">
            <label className="text-[10px] font-black uppercase tracking-widest text-slate-500">Date</label>
            <div className="relative">
              <input type="text" defaultValue="05/24/2024" className="w-full bg-slate-100 border-none rounded-2xl py-4 px-5 text-slate-900 font-bold focus:ring-0" />
              <Calendar size={18} className="absolute right-4 top-1/2 -translate-y-1/2 text-slate-400" />
            </div>
          </div>
          <div className="space-y-3">
            <label className="text-[10px] font-black uppercase tracking-widest text-slate-500">Start Time</label>
            <div className="relative">
              <input type="text" defaultValue="06:00 PM" className="w-full bg-slate-100 border-none rounded-2xl py-4 px-5 text-slate-900 font-bold focus:ring-0" />
              <Clock size={18} className="absolute right-4 top-1/2 -translate-y-1/2 text-slate-400" />
            </div>
          </div>
        </div>

        <div className="space-y-3">
          <label className="text-[10px] font-black uppercase tracking-widest text-slate-500">Duration (Hours)</label>
          <div className="flex gap-2">
            {['1.0', '2.0', '3.0', '4.0+'].map(h => (
              <button 
                key={h}
                type="button"
                className={`flex-1 py-3 rounded-xl font-black text-sm transition-all ${
                  h === '2.0' ? 'bg-[#bef500] text-[#151f00] shadow-lg' : 'bg-slate-100 text-slate-500'
                }`}
              >
                {h}
              </button>
            ))}
          </div>
        </div>

        <div className="space-y-3">
          <label className="text-[10px] font-black uppercase tracking-widest text-slate-500">Venue Location</label>
          <div className="relative">
            <MapPin size={18} className="absolute left-4 top-1/2 -translate-y-1/2 text-[#003461]" />
            <input type="text" placeholder="e.g. Central Arena" className="w-full bg-slate-100 border-none rounded-2xl py-4 pl-12 pr-5 text-slate-900 font-bold focus:ring-0" />
          </div>
        </div>

        <div className="space-y-3">
          <label className="text-[10px] font-black uppercase tracking-widest text-slate-500">Allocated Courts</label>
          <div className="bg-slate-100 p-5 rounded-3xl flex items-center gap-4">
            <div className="w-12 h-12 bg-[#003461] rounded-2xl flex items-center justify-center text-white">
              <Trophy size={24} />
            </div>
            <div className="flex-1">
              <input type="text" placeholder="e.g. 4, 5" className="w-full bg-transparent border-none p-0 text-xl font-black text-[#003461] placeholder:text-slate-300 focus:ring-0" />
            </div>
            <div className="text-[8px] font-black uppercase text-slate-400 tracking-tighter text-right leading-none">
              Multiple<br/>Values OK
            </div>
          </div>
        </div>
      </form>

      <div className="fixed bottom-0 left-0 right-0 p-6 bg-gradient-to-t from-[#f8fafb] via-[#f8fafb] to-transparent pt-12">
        <button className="w-full bg-[#bef500] text-[#151f00] py-5 rounded-2xl font-black text-lg uppercase tracking-tight shadow-2xl shadow-lime-500/20 flex items-center justify-center gap-3 active:scale-95 transition-all">
          Save & Publish Session
          <Send size={20} />
        </button>
      </div>
    </div>
  );
};

const MembersScreen = () => {
  return (
    <div className="pt-20 pb-32 px-6">
      <Header title="Aspire Badminton" />
      
      <div className="mb-10 pt-4">
        <p className="text-[10px] font-black text-slate-400 tracking-[0.2em] uppercase mb-1">Club Directory</p>
        <h2 className="text-5xl font-black text-[#003461] leading-none -ml-1 font-headline">Active <span className="text-[#a6d700]">Members</span></h2>
      </div>

      <div className="relative mb-12">
        <input 
          type="text" 
          placeholder="Find a partner..." 
          className="w-full bg-slate-100 border-none rounded-2xl py-4 pl-12 pr-4 focus:ring-0 font-bold text-slate-900" 
        />
        <LayoutGrid size={20} className="absolute left-4 top-1/2 -translate-y-1/2 text-slate-400" />
        <button className="absolute right-3 top-1/2 -translate-y-1/2 bg-[#003461] text-white p-2 rounded-xl">
          <Settings size={16} />
        </button>
      </div>

      <div className="flex gap-2 mb-8 overflow-x-auto no-scrollbar pb-2">
        {['ALL PLAYERS', 'ELITE', 'PRO', 'ROOKIE'].map((f, i) => (
          <button 
            key={f}
            className={`px-5 py-2.5 rounded-full text-[10px] font-black tracking-widest whitespace-nowrap transition-all ${
              i === 0 ? 'bg-[#bef500] text-[#151f00]' : 'bg-slate-100 text-slate-500'
            }`}
          >
            {f}
          </button>
        ))}
      </div>

      <div className="space-y-4">
        {[
          { name: "Ali Ahmed", status: "COURT 4 ACTIVE", color: "bg-[#bef500]" },
          { name: "Dewan Murshed", status: "LOBBY", color: "bg-slate-300" },
          { name: "Dr. Ashraf", status: "READY TO PLAY", color: "bg-[#bef500]" },
          { name: "Sara Jenkins", status: "COURT 1 ACTIVE", color: "bg-[#bef500]" },
          { name: "Kevin Zhou", status: "WARMING UP", color: "bg-[#bef500]" }
        ].map((m, i) => (
          <div key={i} className="bg-slate-50 p-4 rounded-full flex items-center justify-between hover:bg-slate-100 transition-all group">
            <div className="flex items-center gap-4">
              <div className="relative">
                <div className="w-14 h-14 rounded-full bg-white border-2 border-transparent group-hover:border-[#bef500] transition-all overflow-hidden">
                  <img src={`https://picsum.photos/seed/m${i}/100/100`} alt="" />
                </div>
                <div className={`absolute bottom-0 right-0 w-4 h-4 ${m.color} rounded-full border-2 border-slate-50`} />
              </div>
              <div>
                <h3 className="font-black text-[#003461] text-lg leading-tight font-headline">{m.name}</h3>
                <span className="text-[10px] font-black text-slate-400 tracking-widest flex items-center gap-1">
                  <div className="w-1.5 h-1.5 rounded-full bg-slate-300" /> {m.status}
                </span>
              </div>
            </div>
            <button className="w-12 h-12 rounded-full bg-[#004b87] text-white flex items-center justify-center shadow-lg active:scale-90 transition-all">
              <Phone size={20} fill="currentColor" />
            </button>
          </div>
        ))}
      </div>

      <div className="mt-12 bg-[#003461] rounded-[2.5rem] p-8 relative overflow-hidden">
        <Trophy size={120} className="absolute -right-4 -bottom-4 text-white opacity-5" />
        <h4 className="text-white/40 text-[10px] font-black tracking-[0.2em] uppercase mb-4">Elite Tip</h4>
        <p className="text-2xl text-white font-medium leading-tight relative z-10 font-headline">
          "Precision over power. The shuttlecock respects the angle."
        </p>
        <div className="mt-8 flex items-center gap-3">
          <div className="h-[2px] w-8 bg-[#bef500]" />
          <span className="text-[#bef500] font-black text-[10px] uppercase tracking-widest">Coach M. Khan</span>
        </div>
      </div>
    </div>
  );
};

const PaymentsScreen = () => {
  return (
    <div className="pt-20 pb-32 px-6">
      <Header title="Aspire Badminton" />
      
      <div className="mb-8 pt-4">
        <div className="bg-[#003461] rounded-[2.5rem] p-8 relative overflow-hidden shadow-2xl shadow-blue-900/20">
          <p className="text-white/40 text-[10px] font-black tracking-[0.2em] uppercase mb-4">Member Performance</p>
          <h2 className="text-5xl font-black text-white leading-none font-headline">1/2 <span className="text-2xl text-white/60">MONTHS</span></h2>
          
          <div className="mt-6 h-2 w-full bg-white/10 rounded-full overflow-hidden">
            <div className="h-full w-1/2 bg-[#bef500] rounded-full" />
          </div>

          <div className="grid grid-cols-2 gap-4 mt-8">
            <div className="bg-white/5 p-4 rounded-2xl">
              <p className="text-white/40 text-[8px] font-black uppercase tracking-widest mb-1">Current Streak</p>
              <p className="text-lg font-black text-white">Elite Tier</p>
            </div>
            <div className="bg-white/5 p-4 rounded-2xl">
              <p className="text-white/40 text-[8px] font-black uppercase tracking-widest mb-1">Total Saved</p>
              <p className="text-lg font-black text-white">$45.00</p>
            </div>
          </div>
        </div>
      </div>

      <div className="space-y-6">
        <div className="bg-white rounded-[2.5rem] p-8 shadow-xl shadow-blue-900/5 border border-slate-50">
          <div className="flex justify-between items-start mb-8">
            <div>
              <h3 className="text-xl font-black text-[#003461] font-headline">This Month</h3>
              <p className="text-slate-400 text-sm font-medium">Membership Payment</p>
            </div>
            <span className="bg-red-50 text-red-600 px-4 py-1.5 rounded-full text-[10px] font-black tracking-widest flex items-center gap-2">
              <div className="w-1.5 h-1.5 rounded-full bg-red-600" /> UNPAID
            </span>
          </div>
          
          <div className="flex items-end justify-between">
            <p className="text-5xl font-black text-[#003461] font-headline">$45.00</p>
            <AlertCircle size={24} className="text-slate-200" />
          </div>
        </div>

        <div className="bg-slate-50 rounded-[2.5rem] p-8 relative overflow-hidden">
          <div className="flex items-center gap-3 mb-2">
            <Zap size={20} className="text-[#3c5000]" />
            <h3 className="text-xl font-black text-[#3c5000] font-headline">Early Bird Perk</h3>
          </div>
          <p className="text-slate-500 text-sm mb-6">Maintained streak reward unlocked!</p>
          
          <div className="bg-[#bef500] p-6 rounded-2xl inline-block">
            <p className="text-[10px] font-black text-[#151f00] uppercase tracking-widest mb-1">Next Month</p>
            <p className="text-4xl font-black text-[#151f00] font-headline">FREE</p>
          </div>
        </div>

        <button className="w-full bg-[#004b87] text-white py-6 rounded-2xl font-black text-lg flex items-center justify-center gap-3 shadow-xl shadow-blue-900/10 active:scale-95 transition-all">
          Confirm My Payment
          <ChevronLeft size={24} className="rotate-180 text-[#bef500]" />
        </button>
      </div>
    </div>
  );
};

const ProfileScreen = () => {
  return (
    <div className="pt-20 pb-32 px-6">
      <Header title="Aspire Badminton" rightElement={<div className="w-8 h-8 rounded-full bg-[#004b87] flex items-center justify-center text-[10px] text-white font-bold">AU</div>} />
      
      <div className="mb-10 pt-4">
        <p className="text-[10px] font-black text-slate-400 tracking-[0.2em] uppercase mb-1">Elite Member Profile</p>
        <h1 className="text-6xl font-black text-[#003461] leading-[0.85] font-headline">ALI<br/><span className="text-[#bef500]">AHMED</span></h1>
      </div>

      <div className="space-y-4">
        <div className="bg-slate-100 rounded-[2rem] p-6 flex items-center gap-6 mb-8">
          <div className="relative">
            <div className="w-24 h-24 rounded-3xl overflow-hidden ring-4 ring-[#bef500] shadow-2xl">
              <img src="https://picsum.photos/seed/ali/200/200" alt="Ali" className="w-full h-full object-cover" />
            </div>
            <button className="absolute -bottom-2 -right-2 bg-[#bef500] text-[#151f00] p-2 rounded-xl shadow-lg">
              <Settings size={16} />
            </button>
          </div>
          <div>
            <h2 className="text-2xl font-black text-[#003461] font-headline">Ali Ahmed</h2>
            <p className="text-slate-500 text-xs font-bold uppercase tracking-wider">Member Since 2023</p>
            <span className="inline-block mt-3 bg-[#003461] text-white px-3 py-1 rounded-full text-[8px] font-black tracking-widest uppercase">Premium Plus</span>
          </div>
        </div>

        {[
          { label: "Mobile Number", value: "+971 50 123 4567", icon: Smartphone, color: "bg-blue-50 text-[#003461]" },
          { label: "Home Address", value: "Downtown Burj District, Dubai, UAE", icon: MapPin, color: "bg-blue-50 text-[#003461]" },
          { label: "Emergency Contact", value: "Sara Ahmed (Spouse) • +971 55 987 6543", icon: AlertCircle, color: "bg-red-50 text-red-600" }
        ].map((f, i) => (
          <div key={i} className="bg-white p-5 rounded-3xl flex items-center justify-between shadow-xl shadow-blue-900/[0.02] border border-slate-50">
            <div className="flex items-center gap-4">
              <div className={`w-12 h-12 rounded-2xl flex items-center justify-center ${f.color}`}>
                <f.icon size={20} />
              </div>
              <div>
                <p className="text-[8px] font-black text-slate-400 uppercase tracking-[0.2em] mb-1">{f.label}</p>
                <p className="text-sm font-black text-[#003461]">{f.value}</p>
              </div>
            </div>
            <Settings size={16} className="text-slate-200" />
          </div>
        ))}
      </div>
    </div>
  );
};

const AdminScreen = ({ onAddSession }: { onAddSession: () => void }) => {
  return (
    <div className="pt-20 pb-32 px-6">
      <Header title="Aspire BC" rightElement={<Bell size={20} className="text-slate-400" />} />
      
      <div className="mb-10 pt-4">
        <h1 className="text-5xl font-black text-[#003461] leading-none tracking-tight font-headline">Admin Control</h1>
        <p className="text-slate-500 font-medium mt-2">Manage sessions, memberships, and club logistics.</p>
      </div>

      <div className="space-y-6">
        <button 
          onClick={onAddSession}
          className="w-full bg-[#bef500] text-[#151f00] font-black text-lg py-6 rounded-2xl flex justify-center items-center gap-3 shadow-xl shadow-lime-500/20 active:scale-95 transition-all"
        >
          <Plus size={24} strokeWidth={3} />
          ADD A SESSION
        </button>

        <div className="bg-[#003461] p-8 rounded-[2rem] text-white relative overflow-hidden shadow-2xl shadow-blue-900/20">
          <div className="relative z-10">
            <p className="text-[10px] font-black uppercase tracking-[0.2em] text-white/40 mb-2">Active Members</p>
            <p className="text-6xl font-black italic font-headline">142</p>
          </div>
          <LayoutGrid size={120} className="absolute -right-8 -bottom-8 text-white opacity-5" />
        </div>

        <section className="bg-slate-100 p-6 rounded-[2rem]">
          <div className="flex justify-between items-end mb-6">
            <h2 className="text-xl font-black text-[#003461] font-headline">Pending Approvals</h2>
            <span className="bg-blue-100 text-[#003461] px-3 py-1 rounded-full text-[8px] font-black uppercase tracking-widest">3 NEW REGS</span>
          </div>
          <div className="space-y-3">
            {[
              { name: "Julian D.", level: "Intermediate", time: "2h ago", initials: "JD" },
              { name: "Marcus K.", level: "Advanced", time: "5h ago", initials: "MK" }
            ].map((reg, i) => (
              <div key={i} className="bg-white p-4 rounded-2xl flex items-center justify-between shadow-sm">
                <div className="flex items-center gap-4">
                  <div className="w-12 h-12 bg-slate-100 rounded-full flex items-center justify-center font-black text-[#003461]">{reg.initials}</div>
                  <div>
                    <h3 className="font-black text-slate-900">{reg.name}</h3>
                    <p className="text-[10px] font-bold text-slate-400 uppercase tracking-wider">Level: {reg.level} • {reg.time}</p>
                  </div>
                </div>
                <div className="flex gap-2">
                  <button className="p-2 text-red-500 hover:bg-red-50 rounded-xl transition-colors">
                    <AlertCircle size={20} />
                  </button>
                  <button className="p-2 bg-[#bef500] text-[#151f00] rounded-xl shadow-lg">
                    <CheckCircle2 size={20} />
                  </button>
                </div>
              </div>
            ))}
          </div>
        </section>

        <section className="bg-slate-100 p-6 rounded-[2rem]">
          <h2 className="text-xl font-black text-[#003461] mb-6 font-headline">Guest Requests</h2>
          <div className="bg-white p-4 rounded-2xl flex items-center justify-between shadow-sm">
            <div className="flex gap-4 items-center">
              <div className="w-10 h-10 bg-blue-50 rounded-xl flex items-center justify-center text-[#003461]">
                <Users size={20} />
              </div>
              <div>
                <p className="font-black text-slate-900">Sarah Chen <span className="font-normal text-slate-400">(Guest)</span></p>
                <p className="text-[8px] uppercase font-black text-[#003461] tracking-widest">Team Smash A Invitation</p>
              </div>
            </div>
            <button className="text-[8px] uppercase font-black bg-[#004b87] text-white px-4 py-2.5 rounded-xl active:scale-95 transition-all">
              Approve
            </button>
          </div>
        </section>
      </div>
    </div>
  );
};

export default function App() {
  const [activeTab, setActiveTab] = useState('home');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isAddingSession, setIsAddingSession] = useState(false);

  if (!isLoggedIn) {
    return <LoginScreen onLogin={() => setIsLoggedIn(true)} />;
  }

  if (isAddingSession) {
    return <CreateSessionScreen onBack={() => setIsAddingSession(false)} />;
  }

  return (
    <div className="min-h-screen bg-[#f8fafb] font-body selection:bg-[#bef500] selection:text-[#151f00]">
      <AnimatePresence mode="wait">
        <motion.div
          key={activeTab}
          initial={{ opacity: 0, y: 10 }}
          animate={{ opacity: 1, y: 0 }}
          exit={{ opacity: 0, y: -10 }}
          transition={{ duration: 0.2 }}
        >
          {activeTab === 'home' && <HomeScreen onAddSession={() => setIsAddingSession(true)} />}
          {activeTab === 'members' && <MembersScreen />}
          {activeTab === 'payments' && <PaymentsScreen />}
          {activeTab === 'profile' && <ProfileScreen />}
          {activeTab === 'admin' && <AdminScreen onAddSession={() => setIsAddingSession(true)} />}
        </motion.div>
      </AnimatePresence>

      <BottomNav activeTab={activeTab} setActiveTab={setActiveTab} />
    </div>
  );
}
